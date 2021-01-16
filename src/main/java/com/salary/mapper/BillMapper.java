package com.salary.mapper;

import com.salary.model.Bill;
import com.salary.model.BillMap;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Transactional
public interface BillMapper {
    /**
     * 插入账单信息
     *
     * @param record 账单信息
     * @return 成功：1 失败：0
     */
    int insert(Bill record);

    /**
     * 获取最新账单列表
     *
     * @return bill列表
     */
    List<BillMap> selectLatest(String userId);

    /**
     * 查询账单
     *
     * @param map 查询条件
     * @return bill列表
     */
    List<Bill> selectAll(HashMap<String, Object> map);

    /**
     * 根据id查询账单信息
     *
     * @param date   日期
     * @param userId 工号
     * @return 账单信息
     */
    @Select("select * from bill where date = #{date} and user_id = #{userId} limit 1")
    Bill selectById(@Param("date") Date date, @Param("userId") String userId);

    /**
     * 更新账单
     *
     * @param bill 账单信息
     * @return 成功：1 失败：0
     */
    int update(Bill bill);
}