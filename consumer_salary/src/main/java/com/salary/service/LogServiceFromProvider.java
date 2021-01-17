package com.salary.service;

import com.github.pagehelper.PageInfo;
import com.salary.model.Log;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@Component
@FeignClient("PROVIDER_LOG")
public interface LogServiceFromProvider {

    @GetMapping("/getAllLog")
    PageInfo<Log> getAllLog(@RequestBody HashMap<String, Object> map, @RequestParam int page, @RequestParam int rows);

    @GetMapping("/getAllLogOrigin")
    List<Log> getAllLogOrigin(@RequestBody HashMap<String, Object> map);

    @PostMapping("/deleteById")
    int deleteById(@RequestParam Integer id);
}
