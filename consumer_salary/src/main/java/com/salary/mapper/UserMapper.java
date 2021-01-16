package com.salary.mapper;

import com.salary.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Transactional
public interface UserMapper {

    User selectByPrimaryKey(String id);

}