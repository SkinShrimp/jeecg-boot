package org.jeecg.modules.hospital.monitor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.hospital.monitor.entity.Hospitalmonitor;
import org.jeecg.modules.hospital.monitor.mapper.HospitalmonitorMapper;
import org.jeecg.modules.hospital.monitor.service.IHospitalmonitorService;
import org.jeecg.modules.hospital.monitor.vo.HospitalMonitorVo;
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

    @Override
    public boolean CheckHmIdWithLicit(String id, String str) {
        List list = this.baseMapper.selectHmIdWithLicit(id, str);
        return list!=null&&list.size()>0?true:false;
    }

    @Override
    public List<Hospitalmonitor> selectHospitalMonitorByPerCode(Hospitalmonitor hospitalmonitor, Integer pageNo, Integer pageSize) {
        return this.baseMapper.selectHospitalMonitorByPerCode(hospitalmonitor, (pageNo-1)*pageSize, pageSize);
    }

    @Override
    public Integer selectHospitalMonitorByPerCodeCount(Hospitalmonitor hospitalmonitor) {
        return this.baseMapper.selectHospitalMonitorByPerCodeCount(hospitalmonitor);
    }

    @Override
    public HospitalMonitorVo queryByid(Hospitalmonitor hospitalmonitor) {
        return this.baseMapper.selectByHosId(hospitalmonitor);
    }
}
