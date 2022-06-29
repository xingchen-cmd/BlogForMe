package com.forme.blog.controller;

import com.forme.blog.common.BlogException;
import com.forme.blog.common.ResultResponse;
import com.forme.blog.dto.UserLogin;
import com.forme.blog.dto.UserParam;
import com.forme.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户操作
 */
@RestController
@RequestMapping("/user")
public class UserContallor {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResultResponse login(@RequestBody UserLogin userLogin){
        return userService.login(userLogin);
    }


    @PostMapping("/register")
    public ResultResponse register(@RequestBody @Valid UserParam userParam){
        System.out.println(1);
        return userService.register(userParam);
    }
}

