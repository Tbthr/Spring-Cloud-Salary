package com.salary.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salary.aop.Log;
import com.salary.model.Bill;
import com.salary.service.BillService;
import com.salary.util.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "账单信息")
@RestController
@RequestMapping("/bills")
public class BillController {
    @Resource
    private BillService billService;

    /**
     * 查看账单
     *
     * @param map  查询条件
     * @param page 当前页码
     * @param rows 每页条数
     * @return 根据map筛选后的分页数据
     */
    @Log(info = "SELECT", module = "查看账单")
    @ApiOperation(value = "查看账单", notes = "根据条件筛选(分页)")
    @GetMapping("/info")
    public ApiResult getInfo(@RequestParam HashMap<String, Object> map,
                             @RequestParam(required = false, defaultValue = "1") int page,
                             @RequestParam(required = false, defaultValue = "10") int rows) {

        return ApiResult.builder()
                .code(200)
                .msg("获取成功")
                .data(billService.getInfo(map, page, rows))
                .build();
    }

    /**
     * 查看账单（不分页）
     *
     * @param map 查询条件
     * @return 根据map筛选后的数据
     */
    @Log(info = "SELECT", module = "查看账单")
    @ApiOperation(value = "查看账单", notes = "根据条件筛选(不分页)")
    @GetMapping("/info/origin")
    public ApiResult getInfoOrigin(@RequestParam HashMap<String, Object> map) {

        return ApiResult.builder()
                .code(200)
                .msg("获取成功")
                .data(billService.getInfoOrigin(map))
                .build();
    }

    /**
     * 获取最新账单
     *
     * @return 账单列表
     */
    @GetMapping("/latest")
    public ApiResult getLatestBills(@RequestParam String userId) {
        return ApiResult.builder()
                .code(200)
                .msg("获取成功")
                .data(billService.getLatestBills(userId))
                .build();
    }

    /**
     * 提交账单
     *
     * @param map 提交时间、bill列表
     * @return 是否提交成功
     * @throws ParseException 日期转换异常
     */
    @Log(info = "INSERT", module = "提交账单")
    @ApiOperation(value = "提交账单", notes = "")
    @PostMapping("/submit")
    public ApiResult submit(@RequestBody Map<String, Object> map) throws ParseException {
        return billService.submit(map);
    }

    /**
     * 审批账单
     *
     * @param list bill列表
     * @return 是否操作成功
     */
    @Log(info = "UPDATE", module = "审批账单")
    @ApiOperation(value = "审批账单", notes = "")
    @PostMapping("/check")
    public ApiResult check(@RequestBody List<Bill> list) {
        for (Bill bill : list) {
            int i = billService.updateBill(bill);
            if (i <= 0) {
                return ApiResult.builder()
                        .code(500)
                        .msg("操作失败")
                        .data(null)
                        .build();
            }
        }
        return ApiResult.builder()
                .code(200)
                .msg("操作成功")
                .data(null)
                .build();
    }
}
