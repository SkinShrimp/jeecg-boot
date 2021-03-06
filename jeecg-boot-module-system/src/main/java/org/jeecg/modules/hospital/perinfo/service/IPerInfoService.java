package org.jeecg.modules.hospital.perinfo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.hospital.monitor.entity.Hospitalmonitor;
import org.jeecg.modules.hospital.monitors.entity.MonitorList;
import org.jeecg.modules.hospital.perinfo.entity.PerInfo;

/**
 * @Description: 住院人信息
 * @Author: jeecg-boot
 * @Date:   2020-08-29
 * @Version: V1.0
 */
public interface IPerInfoService extends IService<PerInfo> {

    void updatePerInfoByCode(Hospitalmonitor hospitalmonitor);

    PerInfo selectOne(String percode);
}
