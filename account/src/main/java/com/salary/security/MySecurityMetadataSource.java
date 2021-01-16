package com.salary.security;

import com.salary.mapper.MenuMapper;
import com.salary.mapper.RoleMapper;
import com.salary.model.Menu;
import com.salary.model.Role;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 加载可以访问当前网址的所有角色信息
 */
@Component
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private RoleMapper roleMapper;
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //获取请求的url
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        // 如果地址是 /，这个是登录页，不需要任何角色即可访问，直接返回 null
        if ("/".equals(requestUrl)) {
            return null;
        }
        //获取当前请求需要的角色信息，拿url去menu表中匹配，查看是访问的那个menu下的资源，根据menuId 获取到role信息
        List<Menu> menuList = menuMapper.selectAll();
        for (Menu menu : menuList) {
            //如果请求的路径包含在某个menu的url中，且能访问该资源的角色信息存在
            if (antPathMatcher.match(menu.getPath(), requestUrl)) {
                List<Role> roles = roleMapper.selectRoleByMenuId(menu.getId());
                //定义一个数组，来接收能访问该资源的角色
                List<String> roleNameArray = new ArrayList<>();
                for (Role role : roles) {
                    roleNameArray.add(role.getName());
                }
                return SecurityConfig.createList(roleNameArray.toArray(new String[0]));
            }
        }
        //如果遍历完menu之后没有匹配上，说明访问该资源不需要权限信息，设置一个登陆就能访问的角色
        return SecurityConfig.createList("ROLE_login");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
