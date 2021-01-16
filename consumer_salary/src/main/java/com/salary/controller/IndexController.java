package com.salary.controller;

import com.salary.aop.Log;
import com.salary.service.UserService;
import com.salary.util.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = {"主页"})
@RestController
public class IndexController {
    @Resource
    private UserService userService;

    /**
     * 获取首页统计信息
     *
     * @return 统计信息
     */
    @Log(info = "INFO", module = "获取统计数据")
    @ApiOperation(value = "获取统计数据", notes = "")
    @GetMapping("/index")
    public ApiResult getIndexInfo() {
        return ApiResult.builder()
                .code(200)
                .msg("获取成功")
                .data(userService.getIndexInfo())
                .build();
    }
}
