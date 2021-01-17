package com.salary.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.salary.mapper.LogMapper;
import com.salary.model.Log;
import com.salary.service.LogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/log")
public class LogController {
    @Resource
    private LogService logService;

    @GetMapping("/getAllLog")
    public PageInfo<Log> getAllLog(@RequestBody HashMap<String, Object> map, @RequestParam int page,@RequestParam int rows) {
        return logService.getAllLog(map, page, rows);
    }

    @GetMapping("/getAllLogOrigin")
    public List<Log> getAllLogOrigin(@RequestBody HashMap<String, Object> map) {
        return logService.getAllLogOrigin(map);
    }

    @PostMapping("/deleteById")
    public int deleteById(@RequestParam Integer id) {
        return logService.deleteById(id);
    }
}
