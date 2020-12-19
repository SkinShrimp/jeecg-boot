package org.jeecg.modules.hospital.spotchecktask.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.hospital.monitor.entity.Hospitalmonitor;
import org.jeecg.modules.hospital.spotchecktask.entity.SpotCheckTask;
import org.jeecg.modules.hospital.spotchecktask.vo.SpotCheckTaskVo;

import java.util.HashMap;
import java.util.List;

public interface ISpotCheckTaskService extends IService<SpotCheckTask> {
    List<SpotCheckTask> getUnderSpotCheckTasks();

    SpotCheckTask selectAllById(String id);

    List<SpotCheckTask> getSpotCheckTasks(SpotCheckTaskVo spotCheckTask);
    SpotCheckTask getSpotCheckTaskByHmId(String id);

    List<SpotCheckTask> selectCheckUsers(Hospitalmonitor hospitalmonitor, Integer pageNo, Integer pageSize);

    Integer selectCheckUsersCount(Hospitalmonitor hospitalmonitor);

    void updateTaskType(Hospitalmonitor hospitalmonitor);

    Integer spotCheckTaskListsCount(HashMap parameterMap);

    List<SpotCheckTaskVo> spotCheckTaskLists(HashMap parameterMap);

    List<SpotCheckTaskVo> spotCheckTaskListsAll(HashMap hashMap);
}
