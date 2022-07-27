package com.forme.blog.controller;

import com.forme.blog.common.ResultResponse;
import com.forme.blog.dto.BlogDraftParam;
import com.forme.blog.model.entity.BlogDraft;
import com.forme.blog.service.BlogDraftService;
import com.forme.blog.service.BlogService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user/blogDraft")
public class BlogDraftController {
    @Autowired
    private BlogDraftService blogDraftService;


    @GetMapping("/{blogDraftId}")

    public ResultResponse getBlogDraft(@PathVariable Integer blogDraftId){
        return blogDraftService.getBlogDraft(blogDraftId);
    }

    @PutMapping("/editBlog")
    public ResultResponse addBlogDraft(@RequestBody @Valid BlogDraftParam blogDraftParam){
        return blogDraftService.addBlogDraft(blogDraftParam);
    }

    @PutMapping("/editBlog/{blogDraftId}")
    public ResultResponse updateDraft(@PathVariable Integer blogDraftId,@RequestBody @Valid BlogDraftParam blogDraftParam){
        return blogDraftService.updateDraft(blogDraftId,blogDraftParam);
    }

    @DeleteMapping("/delete/{blogDraftId}")
    public ResultResponse deleteDraft(@PathVariable Integer blogDraftId){
        return blogDraftService.deleteDraft(blogDraftId);
    }

    @GetMapping("")
    public ResultResponse getAllBlogDraft(int page,int pageSize){

        return blogDraftService.getAllBlogDraft(page,pageSize);
    }

}
