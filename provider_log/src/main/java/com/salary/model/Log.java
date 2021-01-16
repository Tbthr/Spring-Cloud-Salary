package com.salary.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@ApiModel("日志实体类")
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