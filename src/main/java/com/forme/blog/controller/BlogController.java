package com.forme.blog.controller;

import com.forme.blog.common.ResultResponse;
import com.forme.blog.dto.BlogListParam;
import com.forme.blog.dto.BlogParam;
import com.forme.blog.dto.ClassifyParam;
import com.forme.blog.dto.TagParam;
import com.forme.blog.model.entity.Classify;
import com.forme.blog.model.entity.Tag;
import com.forme.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 博客管理
 */


@RestController
@RequestMapping("/user/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @PutMapping("/edit")
    public ResultResponse editBlog(@RequestBody @Valid BlogParam blogParam){

        return blogService.postBlog(blogParam);
    }

    @PutMapping("/addTag")
    public ResultResponse addTag(@RequestBody @Valid Tag tag){
        return blogService.addTag(tag);
    }

    @DeleteMapping("/deleteTag")
    public ResultResponse deleteTag(@RequestBody @Valid TagParam tagParam){
        return  blogService.deleteTag(tagParam.getTagId());
    }

    @PutMapping("/addClassify")
    public ResultResponse addClassify(@RequestBody @Valid Classify classify){
        return blogService.addClassify(classify);
    }

    @PutMapping("/updateClassify")
    public ResultResponse updateClassify(@RequestBody @Valid ClassifyParam classifyParam){
        return blogService.updateClassify(classifyParam);
    }

    @DeleteMapping("/deleteClassify/{classifyId}")
    public ResultResponse deleteClassify(@PathVariable("classifyId")  Integer classifyId){
        return blogService.deleteClassify(classifyId);
    }

    @PutMapping("/{classifyId}/addBlog")
    public ResultResponse addBlogToClassify(@PathVariable("classifyId") Integer classifyId, @RequestBody @Valid BlogListParam blogListParam){
        return  blogService.addBlogToClassify(classifyId,blogListParam.getBlogIdList());
    }


}
