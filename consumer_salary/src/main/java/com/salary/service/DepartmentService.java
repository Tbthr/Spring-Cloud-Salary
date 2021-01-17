package com.salary.service;

import com.salary.model.Department;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@FeignClient("PROVIDER-DEPATMENT")
public interface DepartmentService {

}
