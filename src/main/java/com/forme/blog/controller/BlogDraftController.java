package com.forme.blog.controller;

import com.forme.blog.common.ResultResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/blogDraft}")
public class BlogDraftController {
    @GetMapping("/{blogDraftId}")
    public ResultResponse getBlogDraft(@PathVariable Integer blogDraftId){
        return blog
    }

}
