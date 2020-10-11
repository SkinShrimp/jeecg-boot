package org.jeecg.modules.hospital.perinfo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.hospital.monitor.entity.Hospitalmonitor;
import org.jeecg.modules.hospital.monitors.entity.MonitorList;
import org.jeecg.modules.hospital.perinfo.entity.PerInfo;

/**
 * @Description: 住院人信息
 * @Author: jeecg-boot
 * @Date:   2020-08-29
 * @Version: V1.0
 */
public interface PerInfoMapper extends BaseMapper<PerInfo> {

    void updatePerInfoByCode(Hospitalmonitor monitorList);

    PerInfo selectByPerCode(String percode);
}
