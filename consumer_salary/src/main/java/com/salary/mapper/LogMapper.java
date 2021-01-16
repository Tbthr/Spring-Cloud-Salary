package com.salary.mapper;

import com.salary.model.Log;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Transactional
public interface LogMapper {
    /**
     * 插入日志
     *
     * @param record 日志信息
     * @return 成功：1 失败：0
     */
    int insert(Log record);
}