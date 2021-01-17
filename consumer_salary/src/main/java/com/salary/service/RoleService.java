package com.salary.service;


import com.salary.model.Role;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Component
@FeignClient("PROVIDER_ROLE")
public interface RoleService {
    @GetMapping("/selectByName")
    Role selectByNameFromProvider(String name);

    @GetMapping("/getAllFromProvider")
    List<Role> getAllFromProvider();

    @PostMapping("/insertRole")
    int insertRoleFromProvider(Role role);

    @GetMapping("/getAllRoleAuth")
    List<Role> getAllRoleAuthFromProvider();

    @PostMapping("/insertMenuRole")
    int insertMenuRoleFromProvider(Integer rid,Integer mid);

    @PostMapping("/deleteMenuRoleById")
    int deleteMenuRoleByIdFromProvider(Integer id);

    @PostMapping("/deleteByPrimaryKey")
    int deleteByPrimaryKeyFromProvider(Integer id);

    @GetMapping("/selectRoleIdByName")
    public Integer selectRoleIdByNameFromProvider(String name);

    @PostMapping("/init")
    void initFromProvider(Integer id,List<Integer> menuId);

    @PostMapping("/updateRole")
    int updateRoleFromProvider(Role role);

    @GetMapping("/selectByPrimaryKey")
    Role selectByPrimaryKeyFromProvider(Integer id);

}
