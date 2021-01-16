package com.salary.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel("账单信息实体类")
@Data
public class Bill {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @PastOrPresent(message = "日期必须为过去或现在")
    private Date date;

    @NotEmpty(message = "工号不能为空")
    @Length(min = 10, max = 10, message = "工号长度为 10 位")
    @Pattern(regexp = "^[0-9]+$", message = "工号格式为数字")
    private String userId;
    private User user;
    private Department department;

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

    private BigDecimal incomeTax;

    private BigDecimal shouldPay;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date checkTime;

    private Integer checkStatus;

    private String mark;

}