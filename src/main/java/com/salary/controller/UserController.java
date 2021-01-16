package com.salary.controller;

import com.salary.aop.Log;
import com.salary.model.User;
import com.salary.service.DepartmentService;
import com.salary.service.RoleService;
import com.salary.service.UserService;
import com.salary.util.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

@Api(tags = {"员工管理"})
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 查看员工信息
     *
     * @return 所有员工信息
     */
    @Log(info = "SELECT", module = "查看员工信息")
    @ApiOperation(value = "查看员工信息", notes = "返回所有员工的信息")
    @GetMapping("/info")
    public ApiResult getAllUsers() {
        return ApiResult.builder()
                .code(200)
                .msg("所有员工信息")
                .data(userService.getAllUsers())
                .build();
    }

    /**
     * 获取部门信息
     *
     * @return 所有部门信息
     */
    @Log(info = "SELECT", module = "获取部门信息")
    @ApiOperation(value = "获取部门信息", notes = "返回所有部门信息")
    @GetMapping("/add/departInfo")
    public ApiResult getAllDeparts() {
        return ApiResult.builder()
                .code(200)
                .msg("获取成功")
                .data(departmentService.getAllDeparts())
                .build();
    }

    /**
     * 查找员工
     *
     * @param map 查询条件
     * @return 根据map筛选后的数据
     */
    @Log(info = "SELECT", module = "查找员工")
    @ApiOperation(value = "查找员工", notes = "根据条件筛选")
    @GetMapping("/add/userInfo")
    public ApiResult getUserByIF(@RequestParam HashMap<String, Object> map) {
        return ApiResult.builder()
                .code(200)
                .msg("获取成功")
                .data(userService.getUserByIF(map))
                .build();
    }

    /**
     * 根据工号返回可用的用户列表
     *
     * @param userId 工号
     * @return 可用的用户列表
     */
    @Log(info = "SELECT", module = "查找可选员工(查看账单时)")
    @ApiOperation(value = "查找可选员工", notes = "根据条件筛选")
    @GetMapping("/add/userInfo/available")
    public ApiResult getUsersByUserId(@RequestParam String userId) {
        return ApiResult.builder()
                .code(200)
                .msg("获取成功")
                .data(userService.getUsersByUserId(userId))
                .build();
    }

    /**
     * 添加员工
     *
     * @param user     员工信息
     * @param roleName 用户类型
     * @return 是否添加成功
     */
    @Log(info = "INSERT", module = "添加员工")
    @ApiOperation(value = "添加员工", notes = "返回所有员工的信息")
    @PostMapping("/add")
    public ApiResult addUser(@RequestBody User user, @RequestParam("roleName") String roleName) {
        user.setPsd(bCryptPasswordEncoder.encode(user.getPsd()));
        int i = userService.addUser(user);
        int j = userService.addRoleToUser(user.getId(), roleService.selectByName(roleName).getId());
        if (i > 0 && j > 0) {
            return ApiResult.builder()
                    .code(200)
                    .msg("添加成功")
                    .data(userService.getAllUsers())
                    .build();
        }
        return ApiResult.builder()
                .code(500)
                .msg("添加失败")
                .data(null)
                .build();
    }

    /**
     * 删除员工
     *
     * @param map 工号
     * @return 是否删除成功
     */
    @Log(info = "DELETE", module = "删除员工")
    @ApiOperation(value = "删除员工", notes = "返回所有员工的信息")
    @PostMapping("/delete")
    public ApiResult deleteUser(@RequestBody HashMap<String, Object> map) {
        String id = (String) map.get("id");
        int i = userService.deleteUser(id);
        if (i > 0) {
            return ApiResult.builder()
                    .code(200)
                    .msg("删除成功")
                    .data(userService.getAllUsers())
                    .build();
        }
        return ApiResult.builder()
                .code(500)
                .msg("删除失败")
                .data(null)
                .build();
    }

    /**
     * 修改员工
     *
     * @param map 需要修改的用户信息
     * @return 是否修改成功
     */
    @Log(info = "UPDATE", module = "修改员工")
    @ApiOperation(value = "修改员工", notes = "返回所有员工的信息")
    @PostMapping("/update")
    public ApiResult updateUser(@RequestBody HashMap<String, Object> map) {
        String psd = (String) map.get("psd");
        if (psd != null) {
            map.put("psd", bCryptPasswordEncoder.encode(psd));
        }
        int i = userService.updateUser(map);
        if (i > 0) {
            return ApiResult.builder()
                    .code(200)
                    .msg("更新成功")
                    .data(userService.getAllUsers())
                    .build();
        }
        return ApiResult.builder()
                .code(500)
                .msg("更新失败")
                .data(null)
                .build();
    }
}
