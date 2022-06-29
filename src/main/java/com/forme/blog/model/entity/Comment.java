package com.forme.blog.model.entity;


import java.time.LocalDateTime;

import java.io.Serializable;

import lombok.Data;


/**
 * <p>
 * 
 * </p>
 *
 * @author liQuan
 * @since 2022-06-09
 */
@Data

public class Comment{


    private Integer commentId;

    private String commentUser;

    private Integer commentParentId;

    private String commentEmail;

    private Integer commentRootId;

    private String commentContent;

    private LocalDateTime commentDate;


}
