package com.salary.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.salary.mapper.BillMapper;
import com.salary.model.ApiResult;
import com.salary.model.Bill;
import com.salary.model.BillMap;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Validated //参数校验
public class BillService {
    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private BillMapper billMapper;

    /**
     * 查询账单
     *
     * @param map  查询条件
     * @param page 当前页码
     * @param rows 每页条数
     * @return 筛选分页后的账单信息
     */
    public PageInfo<Bill> getInfo(HashMap<String, Object> map, int page, int rows) {
        PageHelper.startPage(page, rows);
        return new PageInfo<>(billMapper.selectAll(map));
    }

    /**
     * 查询账单
     *
     * @param map 查询条件
     * @return 筛选后的账单信息
     */
    public List<Bill> getInfoOrigin(HashMap<String, Object> map) {
        return billMapper.selectAll(map);
    }

    /**
     * 查询账单
     *
     * @param date   日期
     * @param userId 工号
     * @return 账单
     */
    public Bill getBillById(String date, @Length(min = 10, max = 10, message = "账号长度为 10 位") String userId) {
        return billMapper.selectById(date + '%', userId);
    }

    /**
     * 提交账单
     * @param map 数据
     * @return 响应实体类
     * @throws ParseException 转换异常
     */
    public ApiResult submit(Map<String, Object> map) throws ParseException {
        int i, j;
        String s = (String) map.get("date");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
        Date date = simpleDateFormat.parse(s);
//        System.out.println(date);   //Thu Feb 01 00:00:00 CST 2018
//        System.out.println(simpleDateFormat.format(date));  // 2018-02-01
        List<Bill> temp = (List<Bill>) map.get("bills");
        List<Bill> bills = objectMapper.convertValue(temp, new TypeReference<List<Bill>>() {
        }); // 解决 class java.util.LinkedHashMap cannot be cast to class com.salary.model.Bill
        for (Bill bill : bills) {
            bill.setDate(date);
            String format = simpleDateFormat.format(date);
            bill = calculateTax(bill); // 计算个税
            if (getBillById(format, bill.getUserId()) != null) {
                // 如果是重新提交 修改状态
                bill.setCheckStatus(0);
                bill.setMark(null);
                bill.setCheckTime(null);

                i = updateBill(bill);
                if (i <= 0) {
                    return ApiResult.builder()
                            .code(500)
                            .msg("提交错误")
                            .data(null)
                            .build();
                }
            } else {
                j = insertBill(bill);
                if (j <= 0) {
                    return ApiResult.builder()
                            .code(500)
                            .msg("提交错误")
                            .data(null)
                            .build();
                }
            }
        }
        return ApiResult.builder()
                .code(200)
                .msg("提交成功")
                .data(null)
                .build();
    }

    /**
     * 计算个人所得税
     *
     * @param bill 工资详情
     * @return 计算后的工资
     */
    public Bill calculateTax(Bill bill) {
        double taxRate;
        BigDecimal sum = bill.getBasePay()
                .add(bill.getSeniorityPay())
                .add(bill.getPostPay())
                .add(bill.getFoodAllow())
                .add(bill.getTrafficAllow())
                .add(bill.getTravelAllow())
                .add(bill.getOvertimeAllow())
                .add(bill.getOtherAllow());
        //  -1:小于 / 0:等于 / 1:大于
        if (sum.compareTo(new BigDecimal(5000)) < 0) {
            taxRate = 0.0;
        } else if (sum.compareTo(new BigDecimal(8000)) < 0) {
            taxRate = 0.03;
        } else if (sum.compareTo(new BigDecimal(17000)) < 0) {
            taxRate = 0.1;
        } else if (sum.compareTo(new BigDecimal(30000)) < 0) {
            taxRate = 0.2;
        } else if (sum.compareTo(new BigDecimal(40000)) < 0) {
            taxRate = 0.25;
        } else if (sum.compareTo(new BigDecimal(60000)) < 0) {
            taxRate = 0.3;
        } else if (sum.compareTo(new BigDecimal(85000)) < 0) {
            taxRate = 0.35;
        } else taxRate = 0.45;
        BigDecimal cut = bill.getFiof()
                .add(bill.getAbsenceCut())
                .add(bill.getOtherCut());
        BigDecimal incomeTax = sum.subtract(cut).multiply(BigDecimal.valueOf(taxRate));
        bill.setIncomeTax(incomeTax);
        bill.setShouldPay(sum.subtract(incomeTax));
        return bill;
    }

    /**
     * 获取最新账单
     *
     * @return 账单列表
     */
    public List<BillMap> getLatestBills(String userId) {
        List<BillMap> bills = billMapper.selectLatest(userId);
        bills.forEach(bill -> {
            if (bill.getBasePay() == null) {
                bill.setBasePay(new BigDecimal(2500));
            }
            if (bill.getSeniorityPay() == null) {
                bill.setSeniorityPay(new BigDecimal(500));
            }
            if (bill.getPostPay() == null) {
                bill.setPostPay(new BigDecimal(800));
            }
            bill.setFoodAllow(new BigDecimal(0));
            bill.setTrafficAllow(new BigDecimal(0));
            bill.setTravelAllow(new BigDecimal(0));
            bill.setOvertimeAllow(new BigDecimal(0));
            bill.setFiof(new BigDecimal(250));
            bill.setAbsenceCut(new BigDecimal(0));
            bill.setOtherCut(new BigDecimal(0));
        });
        return bills;
    }

    /**
     * 插入账单
     *
     * @param bill 账单信息
     * @return 成功：1 失败：0
     */
    public int insertBill(@Valid Bill bill) {
        return billMapper.insert(bill);
    }

    /**
     * 更新账单
     *
     * @param bill 账单信息
     * @return 成功：1 失败：0
     */
    public int updateBill(@Valid Bill bill) {
        return billMapper.update(bill);
    }
}
