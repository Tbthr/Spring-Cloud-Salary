package com.salary.service;

import com.salary.mapper.MenuMapper;
import com.salary.model.Menu;
import org.hibernate.validator.constraints.Length;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import java.util.List;

@Component
@FeignClient("PROVIDER_MENU")
public interface MenuService {

}
