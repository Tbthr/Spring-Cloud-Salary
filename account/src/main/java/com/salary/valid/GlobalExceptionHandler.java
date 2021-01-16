package com.salary.valid;

import com.salary.util.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice(basePackages = "com.salary.controller")
@Slf4j
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = ServiceException.class)
    public ApiResult serviceExceptionHandler(HttpServletRequest req, ServiceException ex) {
        log.debug("[serviceExceptionHandler]", ex);
        // 包装 ApiResult 结果
        return ApiResult.builder()
                .code(ex.getCode())
                .msg(ex.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ApiResult constraintViolationExceptionHandler(HttpServletRequest req, ConstraintViolationException ex) {
        log.debug("[constraintViolationExceptionHandler]", ex);
        // 拼接错误
        StringBuilder detailMessage = new StringBuilder();
        for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()) {
            // 使用 ; 分隔多个错误
            if (detailMessage.length() > 0) {
                detailMessage.append(";");
            }
            // 拼接内容到其中
            detailMessage.append(constraintViolation.getMessage());
        }
        // 包装 ApiResult 结果
        return ApiResult.builder()
                .code(ServiceExceptionEnum.INVALID_REQUEST_PARAM_ERROR.getCode())
                .msg(ServiceExceptionEnum.INVALID_REQUEST_PARAM_ERROR.getMessage() + ":" + detailMessage.toString())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public ApiResult bindExceptionHandler(HttpServletRequest req, BindException ex) {
        log.debug("[bindExceptionHandler]", ex);
        // 拼接错误
        StringBuilder detailMessage = new StringBuilder();
        for (ObjectError objectError : ex.getAllErrors()) {
            // 使用 ; 分隔多个错误
            if (detailMessage.length() > 0) {
                detailMessage.append(";");
            }
            // 拼接内容到其中
            detailMessage.append(objectError.getDefaultMessage());
        }
        // 包装 ApiResult 结果
        return ApiResult.builder()
                .code(ServiceExceptionEnum.INVALID_REQUEST_PARAM_ERROR.getCode())
                .msg(ServiceExceptionEnum.INVALID_REQUEST_PARAM_ERROR.getMessage() + ":" + detailMessage.toString())
                .build();
    }
}