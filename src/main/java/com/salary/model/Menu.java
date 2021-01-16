package com.salary.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel("权限实体类")
@Data
public class Menu {
    private Integer id;

    private String url;

    private String path;

    private String name;

    private Integer parentId;

    private Integer level;

    private Boolean enabled;

    private List<Menu> children;
}