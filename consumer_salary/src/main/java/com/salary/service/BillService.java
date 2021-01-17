package com.salary.service;

import com.github.pagehelper.PageInfo;
import com.salary.model.Bill;
import com.salary.model.BillMap;
import com.salary.util.ApiResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@FeignClient("PROVIDER-BILL")
public interface BillService {
    /**
     * 查询账单
     *
     * @param map  查询条件
     * @param page 当前页码
     * @param rows 每页条数
     * @return 筛选分页后的账单信息
     */
    @GetMapping("/bill/getInfo")
    public PageInfo<Bill> getInfo(HashMap<String, Object> map, int page, int rows);

    /**
     * 查询账单
     *
     * @param map 查询条件
     * @return 筛选后的账单信息
     */
    @GetMapping("/bill/getInfoOrigin")
    public List<Bill> getInfoOrigin(HashMap<String, Object> map);

    /**
     * 查询账单
     *
     * @param date   日期
     * @param userId 工号
     * @return 账单
     */
    @GetMapping("/bill/getBillById")
    public Bill getBillById(String date, String userId);

    /**
     * 提交账单
     *
     * @param map 数据
     * @return 响应实体类
     */
    @PostMapping("/bill/submit")
    public ApiResult submit(Map<String, Object> map);

    /**
     * 获取最新账单
     *
     * @return 账单列表
     */
    @GetMapping("/bill/getLatestBills")
    public List<BillMap> getLatestBills(String userId);

    /**
     * 插入账单
     *
     * @param bill 账单信息
     * @return 成功：1 失败：0
     */
    @PostMapping("/bill/insertBill")
    public int insertBill(@Valid Bill bill);

    /**
     * 更新账单
     *
     * @param bill 账单信息
     * @return 成功：1 失败：0
     */
    @PostMapping("/bill/updateBill")
    public int updateBill(@Valid Bill bill);
}
