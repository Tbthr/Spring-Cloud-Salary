package com.salary.controller;

import com.salary.model.Role;
import com.salary.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @GetMapping("/selectByName/{name}")
    public Role selectByNameFromProvider(@PathVariable("name") String name){
        return roleService.selectByName(name);
    }

    @GetMapping("/getAllFromProvider")
    public List<Role> getAllFromProvider(){
        return roleService.getAll();
    }

    public int insertRoleFromProvider(Role role){
        return roleService.insertRole(role);
    }

    public List<Role> getAllRoleAuthFromProvider(){
        return roleService.getAllRoleAuth();
    }

    public int insertMenuRoleFromProvider(Integer rid,Integer mid) {
        return roleService.insertMenuRole(rid,mid);
    }

    public int deleteMenuRoleByIdFromProvider(Integer id){
        return roleService.deleteMenuRoleById(id);
    }

    public int deleteByPrimaryKeyFromProvider(Integer id){
        return roleService.deleteByPrimaryKey(id);
    }

    public Integer selectRoleIdByNameFromProvider(String name){
        return roleService.selectRoleIdByName(name);
    }
    public void initFromProvider(Integer id, List<Integer> menuId){
        roleService.init(id, menuId);
    }
    public int updateRoleFromProvider(Role role){
        return roleService.updateRole(role);
    }
    public Role selectByPrimaryKeyFromProvider(Integer id){
        return roleService.selectByPrimaryKey(id);
    }
}
