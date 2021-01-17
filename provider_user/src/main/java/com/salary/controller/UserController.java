package com.salary.controller;

import com.salary.model.IndexRes;
import com.salary.model.User;
import com.salary.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/getUserByIF")
    public List<User> getUserByIFFromProvider(@RequestBody(required = false) HashMap<String, Object> map) {
        return userService.getUserByIF(map);
    }

    @GetMapping("/getUserByPrimaryKey")
    public User getUserByPrimaryKey(@RequestParam String id) {
        return userService.getUserByPrimaryKey(id);
    }

    @GetMapping("/getUsersByUserId")
    public List<User> getUsersByUserIdFromProvider(@RequestParam String userId) {
        return userService.getUsersByUserId(userId);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsersFromProvider() {
        return userService.getAllUsers();
    }

    @GetMapping("/getIndexInfo")
    public Map<String, List<IndexRes>> getIndexInfoFromProvider() {
        return userService.getIndexInfo();
    }

    @PostMapping("/deleteUser")
    public int deleteUserFromProvider(@RequestParam String id) {
        return userService.deleteUser(id);
    }

    @PostMapping("/updateUser")
    public int updateUserFromProvider(@RequestBody HashMap<String, Object> map) {
        return userService.updateUser(map);
    }

    @PostMapping("/addUser")
    public int addUserFromProvider(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping("/addRoleToUser")
    public int addRoleToUserFromProvider(@RequestParam String userId, @RequestParam Integer RoleId) {
        return userService.addRoleToUser(userId, RoleId);
    }

    @PostMapping("/updatePsdById")
    public int updatePsdByIdFromProvider(@RequestParam String id, @RequestParam String psd) {
        return userService.updatePsdById(id, psd);
    }
}
