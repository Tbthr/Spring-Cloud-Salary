package com.salary.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("首页信息实体类")
@Data
public class IndexRes {

    private String mark;

    private Integer cnt;
}
