package com.salary.controller;

import com.salary.aop.Log;
import com.salary.model.User;
import com.salary.security.UserDetail;
import com.salary.service.UserService;
import com.salary.util.ApiResult;
import com.salary.util.SendEmailUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.security.GeneralSecurityException;
import java.util.HashMap;

@RestController
public class ForgetController {
    @Resource
    private UserDetail userDetail;
    @Resource
    private UserService userService;
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 向邮箱发送验证码
     * @param map，id为用户id
     * @return 操作状态码
     * @throws GeneralSecurityException
     * @throws MessagingException
     */
    @Log(info = "INFO",module = "获取验证码")
    @PostMapping("/sendMail")
    public ApiResult sendMail(@RequestBody HashMap<String, Object> map) throws GeneralSecurityException, MessagingException {
        String id = (String) map.get("id");
        User user = userDetail.getUserById(id);
        if (user == null) {
            return ApiResult.builder().code(500).msg("用户不存在").data(null).build();
        }
        String email = user.getEmail();
        // 验证码
        String code = SendEmailUtil.send(email);
        SendEmailUtil.codeMap.put(id, code);
        return ApiResult.builder().code(200).msg("验证码发送成功").data(null).build();
    }

    /**
     * 重置密码
     * @param map，id为用户的id，code为邮箱的验证码，psd为新密码
     * @return 操作的状态码
     */
    @Log(info = "UPDATE",module = "重置密码")
    @PostMapping("/forget")
    public ApiResult forget(@RequestBody HashMap<String, Object> map) {
        String id = (String) map.get("id");
        if (userDetail.getUserById(id) == null) {
            return ApiResult.builder().code(500).msg("用户不存在").data(null).build();
        }
        String psd = (String) map.get("psd");
        psd = bCryptPasswordEncoder.encode(psd);
        String userCode = (String) map.get("code");
        if (SendEmailUtil.codeMap.get(id).equals(userCode)) {
            // 更新密码
            userService.updatePsdById(id, psd);
            return ApiResult.builder().code(200).msg("更改成功").data(null).build();
        }
        return ApiResult.builder().code(500).msg("验证码错误").data(null).build();
    }
}
