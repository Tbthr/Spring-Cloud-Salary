package com.salary.controller;

import com.salary.model.IndexRes;
import com.salary.model.User;
import com.salary.service.UserService;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/getUserByIF")
    public List<User> getUserByIFFromProvider(@RequestBody HashMap<String, Object> map) {
        return userService.getUserByIF(map);
    }

    @GetMapping("/getUserByPrimaryKey")
    public User getUserByPrimaryKeyFromProvider(@RequestParam String id) {
        return userService.getUserByPrimaryKey(id);
    }

    @GetMapping("/getUsersByUserId")
    public List<User> getUsersByUserIdFromProvider(@RequestParam String userId) {
        return  userService.getUsersByUserId(userId);
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
    public int addRoleToUserFromProvider(@RequestParam String userId,@RequestParam Integer RoleId) {
        return userService.addRoleToUser(userId,RoleId);
    }
}
