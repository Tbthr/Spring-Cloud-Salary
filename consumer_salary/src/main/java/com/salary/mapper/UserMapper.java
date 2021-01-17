package com.salary.mapper;

import com.salary.model.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserMapper {

    User getByPrimaryKey(String id);

    @Select("select * from user where id = #{id}")
    User getById(String id);
}