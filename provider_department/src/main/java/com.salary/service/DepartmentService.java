package com.salary.service;

import com.salary.mapper.DepartmentMapper;
import com.salary.model.Department;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartmentService {
    @Resource
    private DepartmentMapper departmentMapper;

    /**
     * 获取部门
     *
     * @return 部门列表
     */
    public List<Department> getAllDeparts() {
        return departmentMapper.selectAll();
    }
}
