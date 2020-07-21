package com.summer.servelet;

import com.summer.annotation.Autowired;
import com.summer.annotation.Bean;
import com.summer.service.UserService;

/**
 * @author panyox
 * @date 2020/7/21 6:34 下午
 */
@Bean
public class ServeletHandler {

    @Autowired
    private UserService userService;

    public void listen() {
        System.out.println("servelet start listening...");
    }

    public void addUser() {
        String res = userService.add();
        System.out.println(res);
    }
}
