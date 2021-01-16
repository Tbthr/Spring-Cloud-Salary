package com.salary.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@ApiModel("用户实体类")
@Data
public class User implements UserDetails, Serializable {

    /**
     * 工号
     */
    @NotEmpty(message = "工号不能为空")
    @Length(min = 10, max = 10, message = "工号长度为 10 位")
    @Pattern(regexp = "^[0-9]+$", message = "工号格式为数字")
    private String id;

    @ApiModelProperty("部门id")
    private String departId;

    @ApiModelProperty("职位")
    private String position;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("年龄")
    @PositiveOrZero(message = "年龄必须大于等于0")
    private Integer age;

    @ApiModelProperty("身份证号码")
    @Length(min = 18, max = 18, message = "身份证号码长度为 18 位")
    private String idCard;

    @ApiModelProperty("邮箱")
    @Email(message = "不符合邮箱格式")
    private String email;

    @ApiModelProperty("手机号码")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "不符合手机号码格式")
    private String phone;

    @ApiModelProperty("密码(默认为身份证后六位)")
    private String psd;

    @ApiModelProperty("工龄")
    @PositiveOrZero(message = "工龄必须大于等于0")
    private Integer workYear;

    @ApiModelProperty("用户类型(0-系统管理员、1-普通员工、2-部门管理员、3-财务管理员)")
    @Min(value = 0, message = "用户类型最小为0")
    @Max(value = 3, message = "用户类型最大为3")
    private Integer userFlag;

    @ApiModelProperty("性别(m、f)")
    @Length(min = 1, max = 1, message = "性别长度为 1 位")
    @Pattern(regexp = "[m,f]", message = "性别标识符必须为 m或f")
    private String gender;

    private Department department;

    private List<Role> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return psd;
    }

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}