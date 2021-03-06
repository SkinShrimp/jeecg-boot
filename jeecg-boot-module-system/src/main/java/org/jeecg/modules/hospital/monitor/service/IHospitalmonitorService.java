package org.jeecg.modules.hospital.monitor.service;

import com.baomidou.mybatisplus.extension.service.IService;
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
public interface IHospitalmonitorService extends IService<Hospitalmonitor> {

    List<MonitorDictHisinfo> monitorRelatedLists(HashMap map);

    Integer monitorRelatedListsCount(HashMap parameterMap);

    boolean CheckHmIdWithLicit(String id, String str);

    List<Hospitalmonitor> selectHospitalMonitorByPerCode(Hospitalmonitor hospitalmonitor, Integer pageNo, Integer pageSize);

    Integer selectHospitalMonitorByPerCodeCount(Hospitalmonitor hospitalmonitor);

    HospitalMonitorVo queryByid(Hospitalmonitor hospitalmonitor);

    Map queryByPerCode(Hospitalmonitor hospitalmonitor);

    Integer selectNoPassCount();

    Integer patientBedPercentageCount(HashMap parameterMap);

    List patientBedPercentageLists(HashMap parameterMap);
}
