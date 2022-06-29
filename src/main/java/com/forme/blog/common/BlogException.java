package com.forme.blog.common;

/**
 * 自定义异常
 */
public class BlogException extends RuntimeException{
    public BlogException(){}
    public BlogException(String message){
        super(message);
    }
    public static void fail(String message){throw  new BlogException(message);}
}
