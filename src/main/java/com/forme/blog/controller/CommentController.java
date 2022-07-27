package com.forme.blog.controller;

import com.forme.blog.common.ResultResponse;
import com.forme.blog.dto.CommentParam;
import com.forme.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/blog")
public class CommentController {
    @Autowired
    CommentService commentService;
    @RequestMapping(value = "/{blogId}/RootComment",method = RequestMethod.GET)
    public ResultResponse getParentComment(@PathVariable int blogId,int page,int sizeLimit){
            return commentService.getParentComment(blogId,page,sizeLimit);
    }

    @RequestMapping(value = "/{blogId}/{parentId}/SonComment",method = RequestMethod.GET)
    public ResultResponse getSonComment(@PathVariable int blogId,@PathVariable int parentId,int page,int sizeLimit){
            return commentService.getSonComment(blogId,parentId,page,sizeLimit);
    }
    @RequestMapping(value = "/comment/{commentId}" ,method=RequestMethod.GET)
    public ResultResponse getComment(@PathVariable int commentId){
            return commentService.getComment(commentId);
    }

    @RequestMapping(value = "/{blogId}/addComment" ,method = RequestMethod.PUT)
    public ResultResponse addComment(@PathVariable int blogId, @Valid @RequestBody CommentParam commentParam){
            return commentService.addComment(blogId,commentParam);
    }

    @RequestMapping(value = "/{blogId}/delete/{commentId}",method = RequestMethod.DELETE)
    public ResultResponse deleteComment(@PathVariable int blogId,@PathVariable int commentId){
            return commentService.deleteComment(blogId,commentId);
    }

}
