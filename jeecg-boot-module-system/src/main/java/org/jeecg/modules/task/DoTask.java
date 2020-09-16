package org.jeecg.modules.task;

import com.sun.javaws.security.AppContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.hospital.spotchecktask.entity.SpotCheckTask;
import org.jeecg.modules.hospital.utils.TaskState;

import java.util.concurrent.Callable;

/**
 * 处理定时任务
 */
@Slf4j
public class DoTask implements Callable<SpotCheckTask> {


	private SpotCheckTask task;

	private DoTaskService doTaskService;

	public DoTask(SpotCheckTask task) {
		this.task = task;
		doTaskService = AppContextUtil.getAppContext().getBean(DoTaskService.class);
	}

	public DoTask(SpotCheckTask task, DoTaskService doTaskService) {
		this.task = task;
		this.doTaskService = doTaskService;
	}

	@Override
	public SpotCheckTask call() throws Exception {

		if (TaskState.TODO == task.getTaskState()) {
			task.setTaskState(TaskState.DOING);
			return task;
		}

		if (task instanceof SpotCheckTask) {
			log.info("处理任务");
			return doTaskService.processTask(task);
		}

		return task;
	}

}
