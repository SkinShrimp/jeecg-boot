package org.jeecg.modules.hospital.spotchecktask.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.hospital.spotchecktask.entity.SpotCheckTask;
import org.jeecg.modules.hospital.spotchecktask.vo.SpotCheckTaskVo;

import java.util.List;

public interface ISpotCheckTaskService extends IService<SpotCheckTask> {
    List<SpotCheckTask> getUnderSpotCheckTasks();

    SpotCheckTask selectAllById(String id);

    List<SpotCheckTask> getSpotCheckTasks(SpotCheckTaskVo spotCheckTask);
    SpotCheckTask getSpotCheckTaskByHmId(String id);
}
