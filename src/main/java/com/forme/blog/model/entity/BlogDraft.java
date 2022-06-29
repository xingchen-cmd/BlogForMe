package com.forme.blog.model.entity;


import lombok.Data;

import java.time.LocalDateTime;


/**
 * <p>
 * 
 * </p>
 *
 * @author liQuan
 * @since 2022-06-09
 */
@Data



public class BlogDraft {




    private Integer blogDraftId;

    private String blogTitle;

    private String blogContent;

    private Integer blogAuthorId;

    private String blogDigest;

    private LocalDateTime createDate;


}
