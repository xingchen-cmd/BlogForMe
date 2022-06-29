package com.forme.blog.controller;

import com.forme.blog.common.BlogException;
import com.forme.blog.common.ResultCode;
import com.forme.blog.common.ResultResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.forme.blog.common.BlogException.fail;

/**
 * 抛出一些还没进入controller层就抛出的异常
 * 这样可以被@RestControllerAdvice读取到同一抛出异常给前端收到
 * 因为在filter抛出的异常@ControllerAdvice无法检测到
 */
@RestController
public class ErrorController {
    @RequestMapping("/401")
    public void filterError(HttpServletRequest request) throws Exception {
        throw (BlogException) request.getAttribute("filter.error");
    }

}
