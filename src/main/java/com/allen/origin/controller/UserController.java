package com.allen.origin.controller;

import com.allen.origin.entity.domain.User;
import com.allen.origin.entity.vo.UserVO;
import com.allen.origin.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/users/{id}")
public class UserController {
    @Resource
    private IUserService userService;

    @GetMapping
    public UserVO getUser(@PathVariable long id, @RequestParam(required = false) List<String> optional) {
        User user = userService.get(id, optional);
        return UserVO.TO_VO_CONVERTER.convert(user);
    }
}
