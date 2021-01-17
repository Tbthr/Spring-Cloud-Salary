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
    @GetMapping("/selectByName")
    Role selectByName(@RequestParam("name") String name);

    @GetMapping("/getAllFromProvider")
    List<Role> getAll();

    @PostMapping("/insertRole")
    int insertRole(@RequestBody Role role);

    @GetMapping("/getAllRoleAuth")
    List<Role> getAllRoleAuth();

    @PostMapping("/insertMenuRole")
    int insertMenuRole(@RequestParam("rid") Integer rid,@RequestParam("mid") Integer mid);

    @PostMapping("/deleteMenuRoleById")
    int deleteMenuRoleById(@RequestParam("id") Integer id);

    @PostMapping("/deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("id") Integer id);

    @GetMapping("/selectRoleIdByName")
    public Integer selectRoleIdByName(@RequestParam("name") String name);

    @PostMapping("/init")
    void init(@RequestParam("id") Integer id,@RequestParam("menuId") List<Integer> menuId);

    @PostMapping("/updateRole")
    int updateRole(@RequestBody Role role);

    @GetMapping("/selectByPrimaryKey")
    Role selectByPrimaryKey(@RequestParam("id") Integer id);

}
