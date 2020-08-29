package org.jeecg.modules.hospital.monitor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.hospital.monitor.entity.Hospitalmonitor;
import org.jeecg.modules.hospital.monitor.mapper.HospitalmonitorMapper;
import org.jeecg.modules.hospital.monitor.service.IHospitalmonitorService;
import org.jeecg.modules.hospital.monitor.vo.MonitorDictHisinfo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Description: 医院患者服务表
 * @Author: jeecg-boot
 * @Date:   2020-08-23
 * @Version: V1.0
 */
@Service

public class HospitalmonitorServiceImpl extends ServiceImpl<HospitalmonitorMapper, Hospitalmonitor> implements IHospitalmonitorService {


    @Override
    public List<MonitorDictHisinfo> monitorRelatedLists(HashMap map) {
        return this.baseMapper.selectRelatedMonitors(map);
    }

    @Override
    public Integer monitorRelatedListsCount(HashMap map) {
        return this.baseMapper.selectRelatedMonitorsCount(map);
    }
}
