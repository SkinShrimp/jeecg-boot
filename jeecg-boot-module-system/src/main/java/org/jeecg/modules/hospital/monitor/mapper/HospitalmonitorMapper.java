package org.jeecg.modules.hospital.monitor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.hospital.monitor.entity.Hospitalmonitor;
import org.jeecg.modules.hospital.monitor.vo.HospitalMonitorVo;
import org.jeecg.modules.hospital.monitor.vo.MonitorDictHisinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 医院患者服务表
 * @Author: jeecg-boot
 * @Date:   2020-08-23
 * @Version: V1.0
 */
public interface HospitalmonitorMapper extends BaseMapper<Hospitalmonitor> {
    List<MonitorDictHisinfo> selectRelatedMonitors(HashMap map);
    Integer selectRelatedMonitorsCount(HashMap map);

    List selectHmIdWithLicit(@Param("id") String id, @Param("type") String type);

    List<Hospitalmonitor> selectHospitalMonitorByPerCode(@Param("hospitalmonitor") Hospitalmonitor hospitalmonitor, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    Integer selectHospitalMonitorByPerCodeCount(Hospitalmonitor hospitalmonitor);

    HospitalMonitorVo selectByHosId(Hospitalmonitor hospitalmonitor);

    Map selectByPerCode(Hospitalmonitor hospitalmonitor);
}
