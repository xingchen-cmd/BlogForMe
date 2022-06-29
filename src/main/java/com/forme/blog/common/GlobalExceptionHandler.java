package com.forme.blog.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 全局异常同一处理
 * @author liquan
 * @date 2022/6/12
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER= LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(value = BlogException.class)
    public ResultResponse error(BlogException e){
        LOGGER.info("捕捉异常成功！");
        //e.printStackTrace();
        return ResultResponse.failed(ResultCode.EORROR,e.getMessage());
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultResponse handleValidException(MethodArgumentNotValidException e){
        BindingResult bindingResult=e.getBindingResult();
        String  message=null;
        if(bindingResult.hasErrors()){
            FieldError fieldError=bindingResult.getFieldError();
            if(fieldError !=null){
                message=fieldError.getField()+fieldError.getDefaultMessage();
            }
        }
        return ResultResponse.failed(ResultCode.VALIDATEFAILED,message);
    }

    @ExceptionHandler(BindException.class)
    public ResultResponse handleValidException(BindException e){
        BindingResult bindingResult=e.getBindingResult();
        String  message=null;
        if(bindingResult.hasErrors()){
            FieldError fieldError=bindingResult.getFieldError();
            if(fieldError !=null){
                message=fieldError.getField()+fieldError.getDefaultMessage();
            }
        }
        return ResultResponse.failed(ResultCode.VALIDATEFAILED,message);
    }


}
