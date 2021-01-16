package com.salary.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BillMap {

    private String userId;

    private String name;

    private String departId;

    private String departName;

    private String position;

    private Integer age;

    private String gender;

    private Integer userFlag;

    private BigDecimal basePay;

    private BigDecimal seniorityPay;

    private BigDecimal postPay;

    private BigDecimal foodAllow;

    private BigDecimal trafficAllow;

    private BigDecimal travelAllow;

    private BigDecimal overtimeAllow;

    private BigDecimal otherAllow;

    private BigDecimal fiof;

    private BigDecimal absenceCut;

    private BigDecimal otherCut;
}
