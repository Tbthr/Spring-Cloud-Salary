package com.salary.service;

import com.salary.model.Department;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;

@Component
@FeignClient("PROVIDER_DEPARTMENT")
public interface DepartmentService {
    /**
     * 获取部门
     *
     * @return 部门列表
     */
    @GetMapping("/getAllDeparts")
    List<Department> getAllDeparts();
}
