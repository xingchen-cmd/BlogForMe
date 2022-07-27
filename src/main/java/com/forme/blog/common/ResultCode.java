package com.forme.blog.common;

public enum ResultCode implements IErrorCode{
    SUCCESS(200,"操作成功"),
    FAILED(500,"操作失败"),
    FORBIDDEN(403,"禁止访问"),
    LOGINFAILED(401,"登录失败"),
    LOGINERROR(415,"账号或密码不正确"),
    EORROR(400,"错误"),
    LOGIN_FAILED(417,"注册失败"),
    VALIDATEFAILED(418,"数据校验错误"),
    JWT_FAILED(419,"jwt验证失败"),
    TAG_EMPTY(420,"tag已存在"),
    TAG_INSERT_FAILED(420,"tag添加失败"),
    TAG_NOTEMPTY(420,"tag不存在"),
    TAG_DELETE_SECCESS(200,"tag删除成功"),
    CLASSIFY_ISEMPTY(421,"classify已经存在"),
    CLASSIFY_CREATE_FAILED(421,"classify创建失败"),
    CLASSIFY_CREATE_SECCESS(200,"classify创建成功"),
    CLASSIFY_UPDATE_FAILED(421,"classify更改失败"),
    CLASSIFY_NOT_EMPTY(421,"classify已经不存在"),
    BLOG_NOT_EMPTY(422,"blog不存在"),
    BLOG_UPDATE_FAILED(422,"blog修改失败"),
    BLOGDRAFT_NOT_EMPTY(423,"草稿箱中该博客不存在"),
    BLOGDRAFT_ADD_FAILED(423,"blogDraft添加失败"),
    BLOGDRAFT_UPDATE_FAILED(423,"blogDraft更新失败"),
    COMMENT_IS_DELETE(424,"评论已删除");

    private int code;
    private String message;

    private ResultCode(int code,String message){
        this.code=code;
        this.message=message;
    }

    public int getCode(){return code;}

    public String getMessage(){return message;}
}
