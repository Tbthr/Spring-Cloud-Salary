package com.salary.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.List;

@Data
public class User{

    /**
     * 工号
     */
    @NotEmpty(message = "工号不能为空")
    @Length(min = 10, max = 10, message = "工号长度为 10 位")
    @Pattern(regexp = "^[0-9]+$", message = "工号格式为数字")
    private String id;

    private String departId;

    private String position;

    private String name;

    @PositiveOrZero(message = "年龄必须大于等于0")
    private Integer age;

    @Length(min = 18, max = 18, message = "身份证号码长度为 18 位")
    private String idCard;

    @Email(message = "不符合邮箱格式")
    private String email;

    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "不符合手机号码格式")
    private String phone;

    private String psd;

    @PositiveOrZero(message = "工龄必须大于等于0")
    private Integer workYear;

    @Min(value = 0, message = "用户类型最小为0")
    @Max(value = 3, message = "用户类型最大为3")
    private Integer userFlag;

    @Length(min = 1, max = 1, message = "性别长度为 1 位")
    @Pattern(regexp = "[m,f]", message = "性别标识符必须为 m或f")
    private String gender;

    private Department department;

    private List<Role> authorities;

}