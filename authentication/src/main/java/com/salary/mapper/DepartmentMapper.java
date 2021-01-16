package com.salary.mapper;

import com.salary.model.Department;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface DepartmentMapper {
    /**
     * 删除部门
     *
     * @param id 部门id
     * @return 成功：1 失败：0
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入部门
     *
     * @param record 部门信息
     * @return 成功：1 失败：0
     */
    int insert(Department record);

    /**
     * 查询部门
     *
     * @param id 部门id
     * @return 成功：1 失败：0
     */
    Department selectByPrimaryKey(String id);

    /**
     * 查询部门
     *
     * @return 部门列表
     */
    List<Department> selectAll();

    /**
     * 更新部门
     *
     * @param record 部门信息
     * @return 成功：1 失败：0
     */
    int updateByPrimaryKey(Department record);
}