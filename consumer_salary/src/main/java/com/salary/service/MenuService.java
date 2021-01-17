package com.salary.service;

import com.salary.mapper.MenuMapper;
import com.salary.model.Menu;
import org.hibernate.validator.constraints.Length;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import java.util.List;

@Component
@FeignClient("PROVIDER_MENU")
public interface MenuService {
    @GetMapping("/etMenuByUserId")
    List<Menu> getMenuByUserIdFromProvider(String id);

    @GetMapping("/getMenuByPrimaryKey")
    Menu getMenuByPrimaryKeyFromProvider(Integer id);

    @GetMapping("/getAll")
    List<Menu> getAllFromProvider();

    @GetMapping("/getAllAsc")
    List<Menu> getAllAscFromProvider();

    @GetMapping("/getByLevel")
    List<Menu> getByLevelFromProvider(Integer level);

    @GetMapping("/getAllAuth")
    List<Menu> getAllAuthFromProvider();

    @PostMapping("/deleteMenuRoleByAll")
    int deleteMenuRoleByAllFromProvider(Integer rid,Integer mid);

    @PostMapping("/getNotAuthByRoleId")
    List<Menu> getNotAuthByRoleIdFromProvider(Integer id);
}
