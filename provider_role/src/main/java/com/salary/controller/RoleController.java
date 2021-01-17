package com.salary.controller;

import com.salary.model.Role;
import com.salary.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @GetMapping("/selectByName")
    public Role selectByNameFromProvider(@RequestParam String name){
        return roleService.selectByName(name);
    }

    @GetMapping("/getAllFromProvider")
    public List<Role> getAllFromProvider(){
        return roleService.getAll();
    }

    @PostMapping("/insertRole")
    public int insertRoleFromProvider(@RequestBody Role role){
        return roleService.insertRole(role);
    }

    @GetMapping("/getAllRoleAuth")
    public List<Role> getAllRoleAuthFromProvider(){
        return roleService.getAllRoleAuth();
    }

    @PostMapping("/insertMenuRole")
    public int insertMenuRoleFromProvider(@RequestParam Integer rid,@RequestParam Integer mid) {
        return roleService.insertMenuRole(rid,mid);
    }

    @PostMapping("/deleteMenuRoleById")
    public int deleteMenuRoleByIdFromProvider(@RequestParam Integer id){
        return roleService.deleteMenuRoleById(id);
    }

    @PostMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKeyFromProvider(@RequestParam Integer id){
        return roleService.deleteByPrimaryKey(id);
    }

    @GetMapping("/selectRoleIdByName")
    public Integer selectRoleIdByNameFromProvider(@RequestParam String name){
        return roleService.selectRoleIdByName(name);
    }

    @PostMapping("/init")
    public void initFromProvider(@RequestParam Integer id,@RequestParam List<Integer> menuId){
        roleService.init(id, menuId);
    }

    @PostMapping("/updateRole")
    public int updateRoleFromProvider(@RequestBody Role role){
        return roleService.updateRole(role);
    }

    @GetMapping("/selectByPrimaryKey")
    public Role selectByPrimaryKeyFromProvider(@RequestParam Integer id){
        return roleService.selectByPrimaryKey(id);
    }
}
