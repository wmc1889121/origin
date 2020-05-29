package com.allen.statemachine.sm;

import com.allen.statemachine.repository.SMConfigurerDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

/**
 * @author Allen Wan
 * @version 1.0
 */
@Component
public class OrderSMProvider {

    @Resource
    private SMConfigurerDao smConfigurerDao;
    @Resource
    private SMActionProvider smActionProvider;
    @Resource
    private SMGuardProvider smGuardProvider;

    private Map<Long/* tenant_id */, OrderSM> table = new ConcurrentHashMap<>();

    public OrderSM getStateMachine(long tenant) {
        OrderSM sm = table.get(tenant);

        if (sm == null) {
            synchronized (this) {
                if (table.get(tenant) == null) {
                    sm = load(tenant);
                    table.put(tenant, sm);
                }
            }
        }

        return sm;
    }

    private OrderSM load(long tenant) {
        List<SMConfigurer> configurers = smConfigurerDao.getByTenant(tenant);
        OrderSM sm = new OrderSM(tenant);
        configurers.forEach(c -> {
            if (c.getType() == 0) {
                sm.withTransaction()
                        .role(c.getRole())
                        .src(c.getSrc()).target(null, c.getTarget())
                        .event(c.getEvent())
                        .action(getAction(c))
                        .end();
            } else {
                List<SMConfigurer> children = smConfigurerDao.getByPid(c.getId());
                ChoiceSMTransaction choiceSMTransaction = sm.withChoice()
                        .role(c.getRole())
                        .src(c.getSrc())
                        .event(c.getEvent());
                for (SMConfigurer branch : children) {
                    if (branch.getGuard() != null) {
                        choiceSMTransaction.first(getGuard(branch), branch.getTarget(), getAction(branch));
                    } else {
                        choiceSMTransaction.last(branch.getTarget(), getAction(branch));
                    }
                }
                choiceSMTransaction.end();
            }
        });
        return sm;
    }

    private SMAction<SMEvent> getAction(SMConfigurer c) {
        return smActionProvider.getAction(c.getAction());
    }

    private Predicate<SMEvent> getGuard(SMConfigurer c) {
        return smGuardProvider.getGuard(c.getGuard());
    }
}
