package com.salary.mapper;

import com.salary.model.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface RoleMapper {
    @Select("select * from role where id in ( select role_id from menu_role where menu_id = #{Menuid} )")
    List<Role> selectRoleByMenuId(Integer MenuId);
}