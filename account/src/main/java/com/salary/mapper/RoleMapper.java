package com.salary.mapper;

import com.salary.model.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    Role selectByPrimaryKey(Integer id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    List<Role> selectRoleByMenuId(Integer MenuId);

    @Select("select * from role where name = #{name}")
    Role selectByName(String name);

    /**
     * 获取角色对应的权限树
     * @param id，角色id
     * @return id对应角色的权限树
     */
    Role selectAuthByPrimaryKey(Integer id);

    /**
     * 插入menu_role表
     * @param rid，角色id
     * @param mid，权限id
     * @return 成功：1 失败：0
     */
    @Insert("insert into menu_role(role_id,menu_id) values(#{rid},#{mid})")
    int insertMenuRole(Integer rid, Integer mid);

    /**
     * 删除menu_role中角色的所有权限
     * @param rid，角色id
     * @return 成功：1 失败：0
     */
    @Delete("delete from menu_role where role_id =#{rid}")
    int deleteMenuRoleByRid(Integer rid);

    /**
     * 根据name获取角色信息
     * @param name，角色的name
     * @return 成功：1 失败：0
     */
    @Select("select id from role where name = #{name}")
    int selectIdByName(String name);

}