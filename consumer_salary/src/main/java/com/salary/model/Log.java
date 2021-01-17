package com.salary.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Log implements Serializable {

    private Integer id;

    private String date;

    private String username;

    private String ip;

    private String module;

    private String info;

    private String content;
}