package com.salary.service;

import com.salary.model.Department;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Component
@FeignClient("PROVIDER-DEPARTMENT")
public interface DepartmentService {
    /**
     * 获取部门
     *
     * @return 部门列表
     */
    @GetMapping("/getAllDeparts")
    List<Department> getAllDeparts();
}
