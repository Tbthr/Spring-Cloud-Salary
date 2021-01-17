package com.salary.security;

import com.salary.mapper.UserMapper;
import com.salary.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserDetail implements UserDetailsService {
    @Resource
    private UserMapper userMapper;

    public User getUserById(String id){
        return userMapper.getById(id);
    }

    @Override
    // 重写的方法不可以参数校验
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userMapper.getByPrimaryKey(id);
        if (user == null) {
            throw new UsernameNotFoundException("工号不存在！");
        }
        return user;
    }
}
