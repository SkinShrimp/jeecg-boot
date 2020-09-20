package org.jeecg.modules.hospital.monitors.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.hospital.monitors.entity.MonitorList;
import org.jeecg.modules.hospital.monitors.vo.MonitorListVo;

import java.util.List;

/**
 * @Description: 服务观察
 * @Author: jeecg-boot
 * @Date:   2020-08-23
 * @Version: V1.0
 */
public interface MonitorListMapper extends BaseMapper<MonitorList> {

    List<MonitorListVo> selectLists(MonitorListVo monitorListVo);

    Integer selectListsCount(MonitorListVo monitorListVo);

    Integer selectUndoMonitorListCount(String id);
}
