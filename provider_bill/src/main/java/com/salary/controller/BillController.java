package com.salary.controller;

import com.github.pagehelper.PageInfo;
import com.salary.model.Bill;
import com.salary.model.BillMap;
import com.salary.service.BillService;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RequestMapping("/bill")
public class BillController {
    @Resource
    private BillService billService;

    /**
     * 查询账单
     *
     * @param map  查询条件
     * @param page 当前页码
     * @param rows 每页条数
     * @return 筛选分页后的账单信息
     */
    @GetMapping("/getInfo")
    public PageInfo<Bill> getInfo(HashMap<String, Object> map, int page, int rows) {
        return billService.getInfo(map, page, rows);
    }

    /**
     * 查询账单
     *
     * @param map 查询条件
     * @return 筛选后的账单信息
     */
    @GetMapping("/getInfoOrigin")
    public List<Bill> getInfoOrigin(HashMap<String, Object> map) {
        return billService.getInfoOrigin(map);
    }

    /**
     * 查询账单
     *
     * @param date   日期
     * @param userId 工号
     * @return 账单
     */
    @GetMapping("/getBillById")
    public Bill getBillById(String date, @Length(min = 10, max = 10, message = "账号长度为 10 位") String userId) {
        return billService.getBillById(date + '%', userId);
    }

    /**
     * 获取最新账单
     *
     * @return 账单列表
     */
    @GetMapping("/getLatestBills")
    public List<BillMap> getLatestBills(String userId) {
        return billService.getLatestBills(userId);
    }

    /**
     * 插入账单
     *
     * @param bill 账单信息
     * @return 成功：1 失败：0
     */
    @PostMapping("/insertBill")
    public int insertBill(@Valid Bill bill) {
        return billService.insertBill(bill);
    }

    /**
     * 更新账单
     *
     * @param bill 账单信息
     * @return 成功：1 失败：0
     */
    @PostMapping("/updateBill")
    public int updateBill(@Valid Bill bill) {
        return billService.updateBill(bill);
    }
}
