package com.salary.controller;

import com.salary.aop.Log;
import com.salary.model.Menu;
import com.salary.model.User;
import com.salary.service.MenuService;
import com.salary.util.ApiResult;
import com.salary.util.JWTtoken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApiIgnore
@RestController
@RequestMapping("/login")
public class LoginController {
    @Resource
    private MenuService menuService;

    /**
     * 返回的登录信息
     *
     * @return 用户的所有信息，用户的权限树，token认证信息
     */
    @Log(info = "LOGIN", module = "登录成功")
    @PostMapping("/success")
    public ApiResult loginSuccess() {
        //获取token
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String token = JWTtoken.setToken(user.getId());
        List<Menu> menus = menuService.getMenuByUserId(user.getId());
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("token", token);
        map.put("menus", menus);
        return ApiResult.builder().code(200).msg("登录成功").data(map).build();
    }

    /**
     * 登录失败
     *
     * @return 返回失败的状态码
     */
    @PostMapping("/failed")
    public ApiResult loginFailure() {
        return ApiResult.builder().code(500).msg("登录失败").data(null).build();
    }
}
