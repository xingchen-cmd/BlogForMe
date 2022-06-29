package com.forme.blog.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserLogin {
    @ApiModelProperty(value = "用户名",required = true)
    private String userName;
    @ApiModelProperty(value = "密码",required = true)
    private String password;
}
