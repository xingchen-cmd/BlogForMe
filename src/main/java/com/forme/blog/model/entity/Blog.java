package com.forme.blog.model.entity;


import java.io.Serializable;
import java.time.LocalDateTime;

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

public class Blog {


    private Integer blogId;

    private String blogTitle;

    private String blogContent;

    private Integer blogAuthorId;

    private LocalDateTime createDate;

    private String blogDigest;


}
