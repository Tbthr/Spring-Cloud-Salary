package com.salary.mapper;

import com.salary.model.Menu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface MenuMapper {

    List<Menu> selectAll();

}