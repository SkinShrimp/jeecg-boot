package org.jeecg.modules.hospital.spotchecktask.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.hospital.spotchecktask.entity.SpotCheckTask;
import org.jeecg.modules.hospital.spotchecktask.vo.SpotCheckTaskVo;
import org.jeecg.modules.hospital.utils.TaskState;

import java.util.List;

public interface SpotCheckTaskMapper extends BaseMapper<SpotCheckTask> {
    List<SpotCheckTask> selectSpotCheckTasks(TaskState taskState);

    SpotCheckTask selectAllById(String id);

    List<SpotCheckTask> selectSpotCheckTasksBySpotCheckTask(SpotCheckTaskVo spotCheckTask);

    SpotCheckTask getSpotCheckTaskByHmId(String id);
}
