package com.allen.statemachine.controller;

import com.allen.statemachine.sm.SMConfigurer;
import com.allen.statemachine.repository.SMConfigurerDao;
import com.allen.statemachine.vo.SMConfigurerVO;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author Allen Wan
 * @version 1.0
 */
@RestController
@RequestMapping("/sm/configurer")
public class SMConfigurerController {

    @Resource
    private SMConfigurerDao smConfigurerDao;

    @PutMapping("/{tenant}")
    public Object config(@PathVariable long tenant, @RequestBody SMConfigurerVO configurer) throws Exception {
        if (tenant != configurer.getTenantId()) {
            throw new IllegalArgumentException("tenantId");
        }

        updateTransactions(configurer);

        return Collections.singletonMap("success", true);
    }

    @Transactional
    public void updateTransactions(SMConfigurerVO configurer) {
        smConfigurerDao.deleteByTenant(configurer.getTenantId());
        configurer.getTransactions().forEach(vo -> {
            SMConfigurer smc = new SMConfigurer();
            Long id = vo.getId();
            if (id != null) {
                smc.setId(id);
            }
            smc.setType(vo.getType());
            smc.setTenantId(configurer.getTenantId());
            smc.setRole(vo.getRole());
            smc.setSrc(vo.getSrc());
            smc.setEvent(vo.getEvent());
            if (vo.getType() == 0) {
                SMConfigurerVO.Target target = vo.getTargets().get(0);
                smc.setGuard(target.getGuard());
                smc.setTarget(target.getTarget());
                smc.setAction(target.getAction());
            }
            long pid = smConfigurerDao.insert(smc);
            if (vo.getType() == 1) {
                updateBranches(configurer.getTenantId(), pid, vo);
            }
        });
    }

    private void updateBranches(long tenantId, long pid, SMConfigurerVO.TransactionVO vo) {
        List<SMConfigurerVO.Target> targets = vo.getTargets();

        targets.forEach(t -> {
            SMConfigurer smc = new SMConfigurer();
            smc.setTenantId(tenantId);
            smc.setType(3);
            smc.setPid(pid);
            smc.setGuard(t.getGuard());
            smc.setTarget(t.getTarget());
            smc.setAction(t.getAction());
            smConfigurerDao.insert(smc);
        });
    }
}
