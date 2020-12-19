package org.jeecg.modules.hospital.version.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.hospital.version.entity.Version;
import org.jeecg.modules.hospital.version.service.IVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hospital/version")
@Slf4j
public class VersionController {
    @Autowired
    private IVersionService versionService;


    @GetMapping(value = "/latest")
    public Result<?> queryLatestVersion() {
        QueryWrapper<Version> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("check_times",100);
        List<Version> list = versionService.list(queryWrapper);
        return Result.ok(list!=null&&list.size()>0?list.get(0):new Version());
    }
}
