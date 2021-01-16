package com.salary.valid;

import lombok.Getter;

@Getter
public enum ServiceExceptionEnum {

    INVALID_REQUEST_PARAM_ERROR(500, "请求参数不合法");

    /**
     * 错误码
     */
    private final int code;
    /**
     * 错误提示
     */
    private final String message;

    ServiceExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}