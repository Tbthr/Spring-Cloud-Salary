package com.salary.controller;

import com.salary.model.Department;
import com.salary.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/depart")
public class DepartmentController {
    @Resource
    private DepartmentService departmentService;

    /**
     * 获取部门
     *
     * @return 部门列表
     */
    @GetMapping("/getAllDeparts")
    public List<Department> getAllDeparts() {
        return departmentService.getAllDeparts();
    }
}
