package com.salary.controller;

import com.salary.model.Menu;
import com.salary.service.MenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private MenuService menuService;

    @GetMapping("/getMenuByUserId")
    public List<Menu> getMenuByUserIdFromProvider(@RequestParam String id) {
        return menuService.getMenuByUserId(id);
    }

    @GetMapping("/getMenuByPrimaryKey")
    public Menu getMenuByPrimaryKeyFromProvider(@RequestParam Integer id) {
        return menuService.getMenuByPrimaryKey(id);
    }

    @GetMapping("/getAll")
    public List<Menu> getAllFromProvider() {
        return menuService.getAll();
    }

    @GetMapping("/getAllAsc")
    public List<Menu> getAllAscFromProvider() {
        return menuService.getAllAsc();
    }

    @GetMapping("/getByLevel")
    public List<Menu> getByLevelFromProvider(@RequestParam Integer level) {
        return menuService.getByLevel(level);
    }

    @GetMapping("/getAllAuth")
    public List<Menu> getAllAuthFromProvider() {
        return menuService.getAllAuth();
    }

    @PostMapping("/deleteMenuRoleByAll")
    public int deleteMenuRoleByAllFromProvider(@RequestParam Integer rid,@RequestParam Integer mid) {
        return menuService.deleteMenuRoleByAll(rid, mid);
    }

    @PostMapping("/getNotAuthByRoleId")
    public List<Menu> getNotAuthByRoleIdFromProvider(@RequestParam Integer id) {
        return menuService.getNotAuthByRoleId(id);
    }
}
