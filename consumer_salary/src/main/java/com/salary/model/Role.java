package com.salary.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.List;

@Data
public class Role implements GrantedAuthority, Serializable {

    private Integer id;

    private String name;

    private String nameZh;

    private List<Menu> menus;

    @Override
    public String getAuthority() {
        return name;
    }
}