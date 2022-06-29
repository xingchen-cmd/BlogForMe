package com.forme.blog.common;

import lombok.Getter;
import lombok.Setter;

import javax.xml.transform.Result;

/**
 * @author liquan
 * @descripe 自定义数据传输
 * @date 2022/6/10
 */

@Getter
@Setter
public class ResultResponse<T> {
    /**
     * 状态码
     */
    private int code;
    /**
     * 提示消息
     */
    private String message;
    /**
     * 数据
     */
    private T data;


    protected ResultResponse(){
    }

    protected ResultResponse(int code,String message,T data){
        this.code=code;
        this.message=message;
        this.data=data;
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param message 信息
     */
    public static <T>ResultResponse<T> seccess(String message,T data){
        return new ResultResponse<T>(ResultCode.SUCCESS.getCode(), message,data);
    }

    /**
     * 成功获取结果
     * @param data 获取的数据
     * @param <T>
     * @return
     */
    public static <T>ResultResponse<T> success(T data){
        return new ResultResponse<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 失败放回结果
     * @param errorCode
     * @param <T>
     * @return
     */
    public static <T>ResultResponse<T> failed(IErrorCode errorCode){
        return new ResultResponse<T>(errorCode.getCode(), errorCode.getMessage(),null);
    }

    /**
     * 失败返回结果
     * @param errorCode
     * @param message
     * @param <T>
     * @return
     */

    public static <T>ResultResponse failed(IErrorCode errorCode,String message){
        return new ResultResponse(errorCode.getCode(),message,null);
    }


 }
