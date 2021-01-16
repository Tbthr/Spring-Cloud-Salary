package com.salary.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@ApiModel("统一响应实体类")
@Data
@AllArgsConstructor
public class ApiResult {

    @ApiModelProperty("状态码")
    private Integer code;

    @ApiModelProperty("描述信息")
    private String msg;

    @ApiModelProperty("返回数据")
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
