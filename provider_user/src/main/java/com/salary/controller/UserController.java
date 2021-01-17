package com.salary.controller;

import com.salary.model.IndexRes;
import com.salary.model.User;
import com.salary.service.UserService;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/getUserByIF")
    public List<User> getUserByIFFromProvider(HashMap<String, Object> map) {
        return userService.getUserByIF(map);
    }

    public User getUserByPrimaryKeyFromProvider(String id) {
        return userService.getUserByPrimaryKey(id);
    }

    public List<User> getUsersByUserIdFromProvider(String userId) {
        return  userService.getUsersByUserId(userId);
    }

    public List<User> getAllUsersFromProvider() {
        return userService.getAllUsers();
    }


    public Map<String, List<IndexRes>> getIndexInfoFromProvider() {
        return userService.getIndexInfo();
    }

    public int deleteUserFromProvider(String id) {
        return userService.deleteUser(id);
    }

    public int updateUserFromProvider(HashMap<String, Object> map) {
        return userService.updateUser(map);
    }

    public int addUserFromProvider(User user) {
        return userService.addUser(user);
    }


    public int addRoleToUserFromProvider(String userId, Integer RoleId) {
        return userService.addRoleToUser(userId,RoleId);
    }
}
