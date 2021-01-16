package com.salary.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("部门实体类")
@Data
public class Department implements Serializable{

    @ApiModelProperty("部门id")
    private String id;

    @ApiModelProperty("部门名称")
    private String departName;
}