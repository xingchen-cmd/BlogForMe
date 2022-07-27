package com.forme.blog.service;

import com.forme.blog.common.ResultResponse;
import com.forme.blog.dto.CommentParam;

public interface CommentService {
    ResultResponse getParentComment(int blogId, int page, int sizeLimit);

    ResultResponse getSonComment(int blogId, int parentId, int page, int sizeLimit);

    ResultResponse addComment(int blogId, CommentParam commentParam);

    ResultResponse deleteComment(int blogId, int commentId);

    ResultResponse getComment(int commentId);
}
