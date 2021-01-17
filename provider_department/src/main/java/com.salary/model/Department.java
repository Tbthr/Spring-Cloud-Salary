package com.salary.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Department implements Serializable {

    private String id;

    private String departName;
}