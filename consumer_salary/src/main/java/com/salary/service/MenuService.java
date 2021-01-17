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

@Service
@FeignClient("PROVIDER-MENU")
public interface MenuService {
    @GetMapping("/getMenuByUserId")
    List<Menu> getMenuByUserId(@RequestParam("id") String id);

    @GetMapping("/getMenuByPrimaryKey")
    Menu getMenuByPrimaryKey(@RequestParam("id") Integer id);

    @GetMapping("/getAll")
    List<Menu> getAll();

    @GetMapping("/getAllAsc")
    List<Menu> getAllAsc();

    @GetMapping("/getByLevel")
    List<Menu> getByLevel(@RequestParam("level") Integer level);

    @GetMapping("/getAllAuth")
    List<Menu> getAllAuth();

    @PostMapping("/deleteMenuRoleByAll")
    int deleteMenuRoleByAll(@RequestParam("rid") Integer rid,@RequestParam("mid") Integer mid);

    @PostMapping("/getNotAuthByRoleId")
    List<Menu> getNotAuthByRoleId(@RequestParam("id") Integer id);
}
