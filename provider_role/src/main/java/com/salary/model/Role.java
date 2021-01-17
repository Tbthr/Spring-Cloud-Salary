package com.salary.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;



import java.util.List;

@ApiModel("角色实体类")
@Data
public class Role {

    @ApiModelProperty("角色id")
    private Integer id;

    @ApiModelProperty("ROLE_**")
    private String name;

    @ApiModelProperty("权限名称")
    private String nameZh;

    private List<Menu> menus;


}