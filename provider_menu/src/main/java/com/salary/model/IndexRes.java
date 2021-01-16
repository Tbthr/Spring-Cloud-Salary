package com.salary.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@ApiModel("首页信息实体类")
@Data
public class IndexRes implements Serializable {

    private String mark;

    private Integer cnt;
}
