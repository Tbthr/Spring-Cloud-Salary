package com.salary.service;

import com.salary.mapper.MenuMapper;
import com.salary.model.Menu;
import org.hibernate.validator.constraints.Length;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import java.util.List;

@Service
@FeignClient("PROVIDER-MENU")
public interface MenuService {
    @GetMapping("/menu/getMenuByUserId")
    List<Menu> getMenuByUserId(@RequestParam("id") String id);

    @GetMapping("/menu/getMenuByPrimaryKey")
    Menu getMenuByPrimaryKey(@RequestParam("id") Integer id);

    @GetMapping("/menu/getAll")
    List<Menu> getAll();

    @GetMapping("/menu/getAllAsc")
    List<Menu> getAllAsc();

    @GetMapping("/menu/getByLevel")
    List<Menu> getByLevel(@RequestParam("level") Integer level);

    @GetMapping("/menu/getAllAuth")
    List<Menu> getAllAuth();

    @PostMapping("/menu/deleteMenuRoleByAll")
    int deleteMenuRoleByAll(@RequestParam("rid") Integer rid,@RequestParam("mid") Integer mid);

    @PostMapping("/menu/getNotAuthByRoleId")
    List<Menu> getNotAuthByRoleId(@RequestParam("id") Integer id);
}
