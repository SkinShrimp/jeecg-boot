package org.jeecg.modules.hospital.perinfo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.hospital.monitor.entity.Hospitalmonitor;
import org.jeecg.modules.hospital.monitors.entity.MonitorList;
import org.jeecg.modules.hospital.perinfo.entity.PerInfo;
import org.jeecg.modules.hospital.perinfo.mapper.PerInfoMapper;
import org.jeecg.modules.hospital.perinfo.service.IPerInfoService;
import org.springframework.stereotype.Service;

/**
 * @Description: 住院人信息
 * @Author: jeecg-boot
 * @Date:   2020-08-29
 * @Version: V1.0
 */
@Service
public class PerInfoServiceImpl extends ServiceImpl<PerInfoMapper, PerInfo> implements IPerInfoService {

    @Override
    public void updatePerInfoByCode(Hospitalmonitor hospitalmonitor) {
        this.baseMapper.updatePerInfoByCode(hospitalmonitor);
    }

    @Override
    public PerInfo selectOne(String percode) {
        return this.baseMapper.selectByPerCode(percode);
    }
}
