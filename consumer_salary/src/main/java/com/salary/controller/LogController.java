package com.salary.controller;

import com.salary.aop.Log;
import com.salary.service.LogServiceFromProvider;
import com.salary.util.ApiResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/sys")
public class LogController {
    @Resource
    private LogServiceFromProvider logServiceFromProvider;

    /**
     * 查看日志
     *
     * @param map  查询条件
     * @param page 当前页码
     * @param rows 每页条数
     * @return 根据map筛选后的分页数据
     */
    @Log(info = "SELECT", module = "查看日志")
    @GetMapping("/log")
    public ApiResult getAllLogs(@RequestParam HashMap<String, Object> map,
                                @RequestParam(required = false, defaultValue = "1") int page,
                                @RequestParam(required = false, defaultValue = "10") int rows) {

        return ApiResult.builder()
                .code(200)
                .msg("获取日志成功")
                .data(logServiceFromProvider.getAllLog(map, page, rows))
                .build();
    }

    /**
     * 查看日志（不分页）
     *
     * @param map  查询条件
     * @return 根据map筛选后的数据
     */
    @Log(info = "SELECT", module = "查看日志")
    @GetMapping("/log/origin")
    public ApiResult getAllLogsOrigin(@RequestParam HashMap<String, Object> map) {

        return ApiResult.builder()
                .code(200)
                .msg("获取日志成功")
                .data(logServiceFromProvider.getAllLogOrigin(map))
                .build();
    }

    /**
     * 删除日志
     *
     * @param ids 日志id列表
     * @return 是否删除成功
     */
    @Log(info = "DELETE", module = "删除日志")
    @PostMapping("/log/delete")
    public ApiResult deleteLogs(@RequestParam List<Integer> ids) {
        for (Integer id : ids) {
            int i = logServiceFromProvider.deleteById(id);
            if (i <= 0) {
                return ApiResult.builder()
                        .code(500)
                        .msg("删除失败")
                        .data(null)
                        .build();
            }
        }
        return ApiResult.builder()
                .code(200)
                .msg("删除成功")
                .data(null)
                .build();
    }
}
