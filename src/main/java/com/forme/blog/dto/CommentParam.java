package com.forme.blog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author liQuan
 * @since 2022-07-25
 */

@Data
public class CommentParam implements Serializable {

    @NotEmpty
    @ApiModelProperty(value = "用户名",required = true)
    private String commentUser;
    @ApiModelProperty(value = "父评论id")
    private Integer commentParentId;
    @Email
    @ApiModelProperty(value = "评论人邮箱",required = true)
    private String commentEmail;
    @ApiModelProperty(value = "根评论id")
    private Integer commentRootId;
    @NotEmpty
    @ApiModelProperty(value = "评论内容")
    private String commentContent;
    @ApiModelProperty(value="评论时间")
    private LocalDateTime commentDate;
}
