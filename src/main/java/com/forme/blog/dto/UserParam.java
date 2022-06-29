package com.forme.blog.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserParam {
    @NotEmpty(message = "不能为空")
    @Size(min = 6,max=20,message = "用户名的长度应为6-20个字符")
    @ApiModelProperty(value = "用户名",required = true)
    private String userName;
    @NotEmpty(message = "不能为空")
    @Pattern(regexp = "^(?![0-9]+$)(?!a-zA-Z]+$)[0-9A-Za-z]{8,20}$",message = "密码应该包含数字，大小写字母，长度在8-20")
    @Size(min=8,max = 20,message = "密码长度应为8-20个字符")
    @ApiModelProperty(value = "密码",required = true)
    private String userPassword;
    @NotEmpty(message = "不能为空")
    @ApiModelProperty(value = "昵称")
    private String nickName;
    @NotEmpty(message = "不能为空")
    @Email(message = "请输入正确的邮箱")
    @ApiModelProperty(value = "邮箱",required = true)
    private String email;
    @ApiModelProperty(value = "个人简介绍")
    private String userInfo;

}
