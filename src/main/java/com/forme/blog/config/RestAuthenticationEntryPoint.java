package com.forme.blog.config;

import cn.hutool.json.JSONUtil;
import com.forme.blog.common.IErrorCode;
import com.forme.blog.common.ResultCode;
import com.forme.blog.common.ResultResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 接口认证失败实现自定义返回结果可能是jwt过期或者未登录
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(ResultResponse.failed(ResultCode.LOGINFAILED,authException.getMessage())));
        response.getWriter().flush();
    }
}
