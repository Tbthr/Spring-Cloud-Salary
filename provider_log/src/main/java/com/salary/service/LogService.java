package com.salary.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.salary.mapper.LogMapper;
import com.salary.model.Log;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;


/**
 * 操作日志存入数据库
 */
@Service
public class LogService {
    @Resource
    private LogMapper logMapper;


    /**
     * 查询所有日志
     *
     * @param page 页码
     * @param rows 页数
     * @return 分页返回日志列表
     */
    public PageInfo<Log> getAllLog(HashMap<String, Object> map, int page, int rows) {
        PageHelper.startPage(page, rows);
        return new PageInfo<>(logMapper.selectAll(map));
    }

    /**
     * 查询所有日志
     *
     * @return 返回日志列表
     */
    public List<Log> getAllLogOrigin(HashMap<String, Object> map) {
        return logMapper.selectAll(map);
    }

    /**
     * 删除日志
     *
     * @param id 日志id
     * @return 成功：1 失败：0
     */
    public int deleteById(Integer id) {
        return logMapper.deleteByPrimaryKey(id);
    }

}