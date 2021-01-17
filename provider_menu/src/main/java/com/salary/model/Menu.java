package com.salary.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Menu implements Serializable {

    private Integer id;

    private String url;

    private String path;

    private String name;

    private Integer parentId;

    private Integer level;

    private Boolean enabled;

    private List<Menu> children;
}