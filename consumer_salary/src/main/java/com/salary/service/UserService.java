package com.salary.service;

import com.salary.model.IndexRes;
import com.salary.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@FeignClient("PROVIDER-USER")
public interface UserService{

    @PostMapping("/user/getUserByIF")
    List<User> getUserByIF(@RequestBody(required = false) HashMap<String, Object> map);

    @GetMapping("/user/getUserByPrimaryKey")
    User getUserByPrimaryKey(@RequestParam("id") String id);

    @GetMapping("/user/getUsersByUserId")
    List<User> getUsersByUserId(@RequestParam("userId") String userId);

    @GetMapping("/user/getAllUsers")
    List<User> getAllUsers();

    @GetMapping("/user/getIndexInfo")
    Map<String, List<IndexRes>> getIndexInfo();

    @PostMapping("/user/deleteUser")
    int deleteUser(@RequestParam("id") String id);

    @PostMapping("/user/updateUser")
    int updateUser(@RequestBody HashMap<String, Object> map);

    @PostMapping("/user/addUser")
    int addUser(@RequestBody User user);

    @PostMapping("/user/addRoleToUser")
    int addRoleToUser(@RequestParam("userId") String userId,@RequestParam("RoleId") Integer RoleId);

    @PostMapping("/user/updatePsdById")
    int updatePsdById(@RequestParam("id") String id,@RequestParam("psd") String psd);
}
