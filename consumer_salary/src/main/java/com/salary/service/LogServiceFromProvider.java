package com.salary.service;

import com.github.pagehelper.PageInfo;
import com.salary.model.Log;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@Service
@FeignClient("PROVIDER-LOG")
public interface LogServiceFromProvider {

    @PostMapping("/log/getAllLog")
    PageInfo<Log> getAllLog(@RequestBody(required = false) HashMap<String, Object> map, @RequestParam("page") int page, @RequestParam("rows") int rows);

    @PostMapping("/log/getAllLogOrigin")
    List<Log> getAllLogOrigin(@RequestBody(required = false) HashMap<String, Object> map);

    @PostMapping("/log/deleteById")
    int deleteById(@RequestParam("id") Integer id);
}
