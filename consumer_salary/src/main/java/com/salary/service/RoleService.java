package com.salary.service;


import com.salary.model.Role;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Service
@FeignClient("PROVIDER-ROLE")
public interface RoleService {
    @GetMapping("/role/selectByName")
    Role selectByName(@RequestParam("name") String name);

    @GetMapping("/role/getAllFromProvider")
    List<Role> getAll();

    @PostMapping("/role/insertRole")
    int insertRole(@RequestBody Role role);

    @GetMapping("/role/getAllRoleAuth")
    List<Role> getAllRoleAuth();

    @PostMapping("/role/insertMenuRole")
    int insertMenuRole(@RequestParam("rid") Integer rid,@RequestParam("mid") Integer mid);

    @PostMapping("/role/deleteMenuRoleById")
    int deleteMenuRoleById(@RequestParam("id") Integer id);

    @PostMapping("/role/deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("id") Integer id);

    @GetMapping("/role/selectRoleIdByName")
    public Integer selectRoleIdByName(@RequestParam("name") String name);

    @PostMapping("/role/init")
    void init(@RequestParam("id") Integer id,@RequestParam("menuId") List<Integer> menuId);

    @PostMapping("/role/updateRole")
    int updateRole(@RequestBody Role role);

    @GetMapping("/role/selectByPrimaryKey")
    Role selectByPrimaryKey(@RequestParam("id") Integer id);

}
