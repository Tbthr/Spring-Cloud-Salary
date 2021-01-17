package com.salary.model;

import lombok.Data;

import java.util.List;

@Data
public class Role {

    private Integer id;

    private String name;

    private String nameZh;

    private List<Menu> menus;


}