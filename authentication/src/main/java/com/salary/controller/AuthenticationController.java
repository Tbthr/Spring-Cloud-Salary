package com.salary.controller;

import com.salary.aop.Log;
import com.salary.model.Menu;
import com.salary.model.Role;
import com.salary.service.MenuService;
import com.salary.service.RoleService;
import com.salary.util.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Api(tags = "权限管理")
@RestController
@RequestMapping("/authority")
public class AuthenticationController {
    @Resource
    MenuService menuService;
    @Resource
    RoleService roleService;

    /**
     * 获取所有权限列表
     *
     * @return 权限列表
     */
    @Log(info = "SELECT", module = "获取权限列表")
    @ApiOperation(value = "获取权限列表", notes = "")
    @GetMapping("/menu")
    public Object getAllMenu() {
        try {
            List<Menu> menus = menuService.getAllAsc();
            return ApiResult.builder()
                    .code(200)
                    .msg("获取成功")
                    .data(menus)
                    .build();
        } catch (Exception e) {
            return ApiResult.builder()
                    .code(500)
                    .msg("获取失败")
                    .build();
        }
    }

    /**
     * 获取所有角色列表，以树状结构为其拥有的权限返回
     *
     * @return 角色列表，以树状结构返回
     */
    @Log(info = "SELECT", module = "获取角色列表")
    @ApiOperation(value = "获取角色列表", notes = "")
    @GetMapping("/role")
    public Object getAllRole() {
        try {
            List<Role> roles = roleService.getAllRoleAuth();
            return ApiResult.builder()
                    .code(200)
                    .msg("获取成功")
                    .data(roles)
                    .build();
        } catch (Exception e) {
            return ApiResult.builder()
                    .code(500)
                    .msg("获取失败")
                    .build();
        }
    }

    /**
     * 增加角色功能
     *
     * @param map，map中name为角色名字，nameZh为角色描述
     * @return 返回最新的角色列表以树状结构
     */
    @Log(info = "INSERT", module = "增加角色")
    @ApiOperation(value = "增加角色", notes = "")
    @PostMapping("/role/add/role")
    public Object addRole(@RequestBody HashMap<String, Object> map) {
        String name = (String) map.get("name");
        name = "ROLE_" + name;
        String nameZh = (String) map.get("nameZh");
        Role role = new Role();
        role.setName(name);
        role.setNameZh(nameZh);
        try {
            roleService.insertRole(role);
            int id = roleService.selectRoleIdByName(name);
            List<Integer> menuId = Arrays.asList(3, 7, 8, 9);
            roleService.init(id, menuId);
            List<Role> roles = roleService.getAllRoleAuth();
            return ApiResult.builder()
                    .code(200)
                    .msg("添加角色成功")
                    .data(roles)
                    .build();
        } catch (Exception e) {
            return ApiResult.builder()
                    .code(500)
                    .msg("添加角色失败")
                    .build();
        }
    }

    /**
     * 获取对应角色没有的权限树
     *
     * @param map，id为角色对应的id
     * @return 该id对应的角色所没有拥有的权限
     */
    @Log(info = "SELECT", module = "获取权限树")
    @ApiOperation(value = "获取权限树", notes = "")
    @PostMapping("/role/add/menutree")
    public Object getMenuTree(@RequestBody HashMap<String, Object> map) {
        int id = (int) map.get("id");
        return ApiResult.builder()
                .code(200)
                .msg("获取权限树成功")
                .data(menuService.getNotAuthByRoleId(id))
                .build();
    }

    /**
     * 为角色添加权限功能
     *
     * @param map，roleId为角色id，menuId为一个想添加的数组
     * @return 最新的角色权限树列表
     */
    @Log(info = "INSERT", module = "添加权限")
    @ApiOperation(value = "添加权限", notes = "")
    @PostMapping("/role/add/menu")
    public Object addMenus(@RequestBody HashMap<String, Object> map) {
        int rid = (int) map.get("roleId");
        List<Integer> mid = (List<Integer>) map.get("menuId");
        try {
            for (Integer i : mid) {
                roleService.insertMenuRole(rid, i);
            }
            List<Role> roles = roleService.getAllRoleAuth();
            return ApiResult.builder()
                    .code(200)
                    .msg("添加权限成功")
                    .data(roles)
                    .build();
        } catch (Exception e) {
            return ApiResult.builder()
                    .code(500)
                    .msg("添加权限失败")
                    .build();
        }
    }

    /**
     * 修改角色信息
     *
     * @param map，id为当前id，name为更改后的name，nameZh为更改后的nameZh
     * @return 最新的角色权限树列表
     */
    @Log(info = "UPDATE", module = "修改角色")
    @ApiOperation(value = "修改角色", notes = "")
    @PostMapping("/role/update")
    public Object updateRole(@RequestBody HashMap<String, Object> map) {
        try {
            int id = (int) map.get("id");
            String name = (String) map.get("name");
            name = "ROLE_" + name;
            String nameZh = (String) map.get("nameZh");
            Role role = roleService.selectByPrimaryKey(id);
            role.setName(name);
            role.setNameZh(nameZh);
            roleService.updateRole(role);
            List<Role> roles = roleService.getAllRoleAuth();
            return ApiResult.builder()
                    .code(200)
                    .msg("修改角色成功")
                    .data(roles)
                    .build();
        } catch (Exception e) {
            return ApiResult.builder()
                    .code(500)
                    .msg("修改角色失败")
                    .build();
        }
    }

    /**
     * 删除角色
     *
     * @param map，获取角色id
     * @return 最新的角色权限树列表
     */
    @Log(info = "DELETE", module = "删除角色")
    @ApiOperation(value = "删除角色", notes = "")
    @PostMapping("/role/delete/roles")
    public Object deleteRoles(@RequestBody HashMap<String, Object> map) {
        List<Integer> rid = (List<Integer>) map.get("id");
        try {
            for (Integer i : rid) {
                roleService.deleteMenuRoleById(i);
                roleService.deleteByPrimaryKey(i);
            }
            List<Role> roles = roleService.getAllRoleAuth();
            return ApiResult.builder()
                    .code(200)
                    .msg("删除角色成功")
                    .data(roles)
                    .build();
        } catch (Exception e) {
            return ApiResult.builder()
                    .code(500)
                    .msg("删除角色失败")
                    .build();
        }
    }

    /**
     * 删除角色对应的权限
     *
     * @param map，roleId为角色的id，menuId为权限的列表
     * @return 最新的角色权限树列表
     */
    @Log(info = "DELETE", module = "删除权限")
    @ApiOperation(value = "删除权限", notes = "")
    @PostMapping("/role/delete/menus")
    public Object deleteMenus(@RequestBody HashMap<String, Object> map) {
        int rid = (int) map.get("roleId");
        List<Integer> mid = (List<Integer>) map.get("menuId");
        try {
            for (Integer i : mid) {
                menuService.deleteMenuRoleByAll(rid, i);
            }
            List<Role> roles = roleService.getAllRoleAuth();
            return ApiResult.builder()
                    .code(200)
                    .msg("删除权限成功")
                    .data(roles)
                    .build();
        } catch (Exception e) {
            return ApiResult.builder()
                    .code(500)
                    .msg("删除权限失败")
                    .build();
        }
    }
}
