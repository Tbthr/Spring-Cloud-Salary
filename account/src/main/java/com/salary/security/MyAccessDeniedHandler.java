package com.salary.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salary.util.ApiResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 权限不足时的对应的操作
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ApiResult result = ApiResult.builder().code(401).msg("没有权限").data(null).build();
        out.write(objectMapper.writeValueAsString(result));
        out.flush();
        out.close();
    }
}
