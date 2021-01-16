package com.salary.service;

import com.salary.mapper.UserMapper;
import com.salary.model.IndexRes;
import com.salary.model.User;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Validated
public class UserService implements UserDetailsService {
    @Resource
    private UserMapper userMapper;

    @Override
    // 重写的方法不可以参数校验
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            throw new UsernameNotFoundException("工号不存在！");
        }
        return user;
    }

    /**
     * 模糊查找
     *
     * @param map name和id
     * @return 返回符合条件的用户信息
     */
    public List<User> getUserByIF(HashMap<String, Object> map) {
        return userMapper.selectByIF(map);
    }

    public User getUserByPrimaryKey(
            @NotEmpty(message = "工号不能为空")
            @Length(min = 10, max = 10, message = "工号长度为 10 位")
            @Pattern(regexp = "^[0-9]+$", message = "工号格式为数字") String id) {

        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据工号返回可用的用户列表
     *
     * @param userId 工号
     * @return 可用的用户列表
     */
    public List<User> getUsersByUserId(String userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user.getUserFlag() == 1) {
            return Collections.singletonList(user);
        } else if (user.getUserFlag() == 2) {
            return userMapper.selectUsersByUserId(userId);
        } else {
            return userMapper.selectAll();
        }
    }

    /**
     * 获取所有的用户信息
     *
     * @return 所有的用户信息
     */
    public List<User> getAllUsers() {
        return userMapper.selectAll();
    }

    /**
     * 获取首页统计信息
     *
     * @return 统计信息
     */
    public Map<String, List<IndexRes>> getIndexInfo() {
        Map<String, List<IndexRes>> map = new HashMap<>();
        List<IndexRes> age = userMapper.selectAge();
        System.out.println("age = " + age);
        List<IndexRes> depart = userMapper.selectDepart();
        System.out.println("depart = " + depart);
        List<IndexRes> gender = userMapper.selectGender();
        System.out.println("gender = " + gender);
        List<IndexRes> position = userMapper.selectPosition();
        System.out.println("position = " + position);
        map.put("age", age);
        map.put("depart", depart);
        map.put("gender", gender);
        map.put("position", position);
        return map;
    }

    public int deleteUser(
            @NotEmpty(message = "工号不能为空")
            @Length(min = 10, max = 10, message = "工号长度为 10 位")
            @Pattern(regexp = "^[0-9]+$", message = "工号格式为数字") String id) {

        return userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 动态更新用户数据
     *
     * @param map 需要更新的用户信息
     * @return
     */
    public int updateUser(HashMap<String, Object> map) {
        return userMapper.updateByPrimaryKey(map);
    }

    /**
     * 插入信息
     *
     * @param user 要修改的User信息
     * @return
     */
    public int addUser(@Valid User user) {
        return userMapper.insert(user);
    }

    /**
     * 给用户赋予角色
     *
     * @param userId，用户id
     * @param RoleId，角色id
     * @return
     */
    public int addRoleToUser(
            @NotEmpty(message = "工号不能为空")
            @Length(min = 5, max = 16, message = "工号长度为 5-16 位")
            @Pattern(regexp = "^[0-9]+$", message = "工号格式为数字") String userId,
            @Min(value = 1L, message = "角色id必须大于 0") Integer RoleId) {

        return userMapper.addRoleToUser(userId, RoleId);
    }

}
