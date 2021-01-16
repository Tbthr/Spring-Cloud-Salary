package com.salary.mapper;

import com.salary.model.Log;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Transactional
public interface LogMapper {
    /**
     * 删除日志
     *
     * @param id 日志id
     * @return 成功：1 失败：0
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入日志
     *
     * @param record 日志信息
     * @return 成功：1 失败：0
     */
    int insert(Log record);

    /**
     * 查询日志
     *
     * @param id 日志id
     * @return 日志
     */
    Log selectByPrimaryKey(Integer id);

    /**
     * 查询日志
     *
     * @param map 查询条件
     * @return 日志列表
     */
    List<Log> selectAll(HashMap<String, Object> map);
}