package org.jeecg.modules.task;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.hospital.spotchecktask.entity.SpotCheckTask;
import org.jeecg.modules.hospital.spotchecktask.service.ISpotCheckTaskService;
import org.jeecg.modules.hospital.utils.TaskState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.concurrent.Callable;

/**
 * 处理定时任务
 */
@Slf4j
public class DoTask implements Callable<SpotCheckTask> {
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private SpotCheckTask task;
	@Autowired
	private ISpotCheckTaskService spotCheckTaskService;
	private DoTaskService doTaskService;

	public DoTask(SpotCheckTask task) {
		this.task = task;
		doTaskService = applicationContext.getBean(DoTaskService.class);
	}

	public DoTask(SpotCheckTask task, DoTaskService doTaskService) {
		this.task = task;
		this.doTaskService = doTaskService;
	}
	public DoTask(SpotCheckTask task, DoTaskService doTaskService,ISpotCheckTaskService spotCheckTaskService) {
		this.task = task;
		this.doTaskService = doTaskService;
		this.spotCheckTaskService=spotCheckTaskService;
	}
	@Override
	public SpotCheckTask call() throws Exception {

		if (TaskState.TODO == task.getTaskState()) {
			task.setTaskState(TaskState.DOING);
			//同步更新数据库中的状态
			if(task.getId()!=null){
				UpdateWrapper updateWrapper = new UpdateWrapper();
				updateWrapper.eq("id",task.getId());
				updateWrapper.set("task_state","DOING");
				spotCheckTaskService.update(updateWrapper);
			}
			return task;
		}

		if (task instanceof SpotCheckTask) {
			log.info("处理任务");
			return doTaskService.processTask(task);
		}

		return task;
	}

}
