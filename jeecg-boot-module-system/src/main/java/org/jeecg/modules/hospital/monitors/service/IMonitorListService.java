package org.jeecg.modules.hospital.monitors.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.hospital.monitors.entity.MonitorList;
import org.jeecg.modules.hospital.monitors.vo.MonitorListVo;

import java.util.List;

/**
 * @Description: 服务观察
 * @Author: jeecg-boot
 * @Date:   2020-08-23
 * @Version: V1.0
 */
public interface IMonitorListService extends IService<MonitorList> {

    List<MonitorListVo> queryList(MonitorListVo monitorListVo);

    Integer queryListCount(MonitorListVo monitorListVo);
}
