package org.jeecg.modules.hospital.spotchecktask.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.hospital.monitor.entity.Hospitalmonitor;
import org.jeecg.modules.hospital.spotchecktask.entity.SpotCheckTask;
import org.jeecg.modules.hospital.spotchecktask.mapper.SpotCheckTaskMapper;
import org.jeecg.modules.hospital.spotchecktask.service.ISpotCheckTaskService;
import org.jeecg.modules.hospital.spotchecktask.vo.SpotCheckTaskVo;
import org.jeecg.modules.hospital.utils.TaskState;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class SpotCheckTaskImpl  extends ServiceImpl<SpotCheckTaskMapper, SpotCheckTask> implements ISpotCheckTaskService {
    @Override
    public List<SpotCheckTask> getUnderSpotCheckTasks() {
        return getSpotCheckTasks(TaskState.DOING);
    }

    @Override
    public SpotCheckTask selectAllById(String id) {
        return this.baseMapper.selectAllById(id);
    }

    @Override
    public List<SpotCheckTask> getSpotCheckTasks(SpotCheckTaskVo spotCheckTask) {
        return this.baseMapper.selectSpotCheckTasksBySpotCheckTask(spotCheckTask);
    }

    @Override
    public SpotCheckTask getSpotCheckTaskByHmId(String id) {
        return this.baseMapper.getSpotCheckTaskByHmId(id);
    }

    @Override
    public List<SpotCheckTask> selectCheckUsers(Hospitalmonitor hospitalmonitor, Integer pageNo, Integer pageSize) {
        return this.baseMapper.selectCheckUsers(hospitalmonitor,(pageNo-1)*pageNo,pageSize);
    }

    @Override
    public Integer selectCheckUsersCount(Hospitalmonitor hospitalmonitor) {
        return this.baseMapper.selectCheckUsersCount(hospitalmonitor);
    }

    @Override
    public void updateTaskType(Hospitalmonitor hospitalmonitor) {
     this.baseMapper.   updateTaskType(hospitalmonitor);
    }

    @Override
    public Integer spotCheckTaskListsCount(HashMap parameterMap) {
        return this.baseMapper.selectSpotCheckTaskListsCount(parameterMap);
    }

    @Override
    public List<SpotCheckTaskVo>  spotCheckTaskLists(HashMap parameterMap) {
        return this.baseMapper.selectSpotCheckTaskLists(parameterMap);
    }

    @Override
    public List<SpotCheckTaskVo> spotCheckTaskListsAll(HashMap parameterMap) {
        return this.baseMapper.selectSpotCheckTaskListsAll(parameterMap);
    }


    private List<SpotCheckTask> getSpotCheckTasks(TaskState taskState) {
        return this.baseMapper.selectSpotCheckTasks(taskState);
    }
}
