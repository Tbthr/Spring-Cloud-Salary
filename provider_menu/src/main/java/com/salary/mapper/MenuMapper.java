package com.salary.mapper;

import com.salary.model.Menu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface MenuMapper {
    /**
     * 根据权限id删除权限
     *
     * @param id，权限id
     * @return 成功：1 失败：0
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入权限
     *
     * @param record，权限的所有信息
     * @return 成功：1 失败：0
     */
    int insert(Menu record);

    /**
     * 根据权限id筛选权限
     *
     * @param id，权限id
     * @return 成功：1 失败：0
     */
    Menu selectByPrimaryKey(Integer id);

    /**
     * 获取所有权限
     *
     * @return 权限列表
     */
    List<Menu> selectAll();

    /**
     * 获取侧边栏的所有权限
     *
     * @return 权限列表
     */
    @Select("select * from menu where id != 1 and id!= 2 order by id")
    List<Menu> selectAllAsc();

    /**
     * 更新权限信息
     *
     * @param record，权限的最新信息
     * @return 成功：1 失败：0
     */
    int updateByPrimaryKey(Menu record);

    /**
     * 获取用户的权限树
     *
     * @param id，用户id
     * @return id对应用户的权限树
     */
    List<Menu> selectByUserId(String id);

    /**
     * 根据权限等级筛选权限
     *
     * @param level 等级
     * @return 对应权限列表
     */
    @Select("select * from menu where level = #{level}")
    List<Menu> selectByLevel(Integer level);

    /**
     * 根据parentId获取权限列表(递归查询)
     *
     * @param id ，权限的parentId
     * @return 对应的权限列表
     */
    List<Menu> selectAuthByPid(Integer id);

    /**
     * 获取权限树（递归查询）
     *
     * @return 所有的权限树
     */
    List<Menu> selectAllAuth();

    /**
     * 删除menu_role表的信息
     *
     * @param rid，角色id
     * @param mid，权限id
     * @return 成功：1 失败：0
     */
    @Delete("delete from menu_role where role_id = #{rid} and menu_id = #{mid}")
    int deleteMenuRoleByAll(Integer rid, Integer mid);

    /**
     * 获取角色没有获取的权限的权限树
     *
     * @param id 角色id
     * @return id对应角色的权限树
     */
    List<Menu> getNotAuthByRoleId(Integer id);

}