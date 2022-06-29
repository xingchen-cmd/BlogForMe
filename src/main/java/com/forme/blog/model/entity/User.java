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

public class User  {


    private Integer userId;

    private String userName;

    private String userPassword;

    private String nickName;

    private String userInfo;

    private String email;

    private LocalDateTime createDate;


}
