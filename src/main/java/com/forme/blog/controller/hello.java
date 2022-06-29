package com.forme.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class hello {
    @GetMapping("/hello")
    public String testhello(){
        return "hello";
    }

    @GetMapping("/test")
    public String text(){
        return "hello world!";
    }

}
