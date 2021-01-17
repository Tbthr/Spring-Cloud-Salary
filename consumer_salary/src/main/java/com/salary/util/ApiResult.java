package com.salary.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResult {

    private Integer code;

    private String msg;

    private Object data;

    public static Builder builder() {
        return new Builder();
    }

    //静态内部类，这里使用Builder设计模式
    public static class Builder {
        private Integer code;
        private String msg;
        private Object data;

        public Builder code(Integer code) {
            this.code = code;
            return this;
        }

        public Builder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder data(Object data) {
            this.data = data;
            return this;
        }

        public ApiResult build() {
            return new ApiResult(code, msg, data);
        }
    }
}
