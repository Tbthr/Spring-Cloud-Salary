package com.salary.controller;

import com.salary.model.Menu;
import com.salary.service.MenuService;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private MenuService menuService;

    public List<Menu> getMenuByUserIdFromProvider(String id) {
        return menuService.getMenuByUserId(id);
    }

    public Menu getMenuByPrimaryKeyFromProvider(Integer id) {
        return menuService.getMenuByPrimaryKey(id);
    }

    public List<Menu> getAllFromProvider() {
        return menuService.getAll();
    }


    public List<Menu> getAllAscFromProvider() {
        return menuService.getAllAsc();
    }


    public List<Menu> getByLevelFromProvider(Integer level) {
        return menuService.getByLevel(level);
    }


    public List<Menu> getAllAuthFromProvider() {
        return menuService.getAllAuth();
    }


    public int deleteMenuRoleByAllFromProvider(Integer rid,Integer mid) {
        return menuService.deleteMenuRoleByAll(rid, mid);
    }

    public List<Menu> getNotAuthByRoleIdFromProvider(Integer id) {
        return menuService.getNotAuthByRoleId(id);
    }
}
