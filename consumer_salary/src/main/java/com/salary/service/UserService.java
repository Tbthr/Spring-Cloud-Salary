package com.salary.service;

import com.salary.mapper.UserMapper;
import com.salary.model.IndexRes;
import com.salary.model.User;
import org.hibernate.validator.constraints.Length;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@FeignClient("PROVIDER_USER")
public interface UserService{

    @GetMapping("/getUserByIF")
    List<User> getUserByIFFromProvider(HashMap<String, Object> map);

    @GetMapping("/getUserByPrimaryKey")
    User getUserByPrimaryKeyFromProvider(String id);

    @GetMapping("/getUsersByUserId")
    List<User> getUsersByUserIdFromProvider(String userId);

    @GetMapping("/getAllUsers")
    List<User> getAllUsersFromProvider();

    @GetMapping("/getIndexInfo")
    Map<String, List<IndexRes>> getIndexInfoFromProvider();

    @PostMapping("/deleteUser")
    int deleteUserFromProvider(String id);

    @PostMapping("/updateUser")
    int updateUserFromProvider(HashMap<String, Object> map);

    @PostMapping("/addUser")
    int addUserFromProvider(User user);

    @PostMapping("/addRoleToUser")
    int addRoleToUserFromProvider(String userId,Integer RoleId);
}
