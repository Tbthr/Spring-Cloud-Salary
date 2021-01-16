package com.salary.service;

import com.salary.mapper.RoleMapper;
import com.salary.model.Role;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class RoleService {
    @Resource
    private RoleMapper roleMapper;

    /**
     * 根据name获取角色
     *
     * @param name ROLE_XX
     * @return 角色
     */
    public Role selectByName(@NotBlank(message = "权限名称不能为空") String name) {
        return roleMapper.selectByName(name);
    }

    /**
     * 获取所有角色
     *
     * @return 角色列表
     */
    public List<Role> getAll() {
        return roleMapper.selectAll();
    }

    /**
     * 插入角色
     *
     * @param role 角色
     * @return 是否成功
     */
    public int insertRole(@Valid Role role) {
        return roleMapper.insert(role);
    }

    /**
     * 获取所有角色对应的权限树
     *
     * @return id对应角色的权限树
     */
    public List<Role> getAllRoleAuth() {
        List<Role> roles = getAll();
        List<Role> roleList = new ArrayList<>();
        for (Role role : roles) {
            Role role1 = roleMapper.selectAuthByPrimaryKey(role.getId());
            roleList.add(role1);
        }
        return roleList;
    }

    /**
     * 插入menu_role表
     *
     * @param rid 角色id
     * @param mid 权限id
     * @return
     */
    public int insertMenuRole(@Min(value = 1L, message = "角色id必须大于 0") Integer rid,
                              @Min(value = 1L, message = "权限id必须大于 0") Integer mid) {
        return roleMapper.insertMenuRole(rid, mid);
    }

    /**
     * 删除menu_role中角色的所有权限
     *
     * @param id 角色id
     * @return
     */
    public int deleteMenuRoleById(@Min(value = 1L, message = "角色id必须大于 0") Integer id) {
        return roleMapper.deleteMenuRoleByRid(id);
    }

    public int deleteByPrimaryKey(@Min(value = 1L, message = "角色id必须大于 0") Integer id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据name获取角色信息
     *
     * @param name，角色的name
     * @return
     */
    public Integer selectRoleIdByName(@NotBlank(message = "权限名称不能为空") String name) {
        return roleMapper.selectIdByName(name);
    }

    /**
     * 初始化角色的权限
     *
     * @param id     角色id
     * @param menuId 权限id集合
     */
    public void init(Integer id, List<Integer> menuId) {
        for (Integer i : menuId) {
            roleMapper.insertMenuRole(id, i);
        }
    }

    public int updateRole(@Valid Role role) {
        return roleMapper.updateByPrimaryKey(role);
    }

    public Role selectByPrimaryKey(@Min(value = 1L, message = "角色id必须大于 0") Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }
}
