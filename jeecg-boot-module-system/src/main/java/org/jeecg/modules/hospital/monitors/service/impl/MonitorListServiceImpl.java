package org.jeecg.modules.hospital.monitors.service.impl;

import org.jeecg.modules.hospital.monitors.entity.MonitorList;
import org.jeecg.modules.hospital.monitors.mapper.MonitorListMapper;
import org.jeecg.modules.hospital.monitors.service.IMonitorListService;
import org.jeecg.modules.hospital.monitors.vo.MonitorListVo;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 服务观察
 * @Author: jeecg-boot
 * @Date:   2020-08-23
 * @Version: V1.0
 */
@Service
public class MonitorListServiceImpl extends ServiceImpl<MonitorListMapper, MonitorList> implements IMonitorListService {

    @Override
    public List<MonitorListVo> queryList(MonitorListVo monitorListVo) {
        return this.baseMapper.selectLists(monitorListVo);
    }

    @Override
    public Integer queryListCount(MonitorListVo monitorListVo) {
        return this.baseMapper.selectListsCount(monitorListVo);
    }
}
