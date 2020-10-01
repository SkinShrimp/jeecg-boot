package org.jeecg.modules.task;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.hospital.spotchecktask.entity.SpotCheckTask;
import org.jeecg.modules.hospital.spotchecktask.service.ISpotCheckTaskService;
import org.jeecg.modules.hospital.utils.TaskState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;
@Slf4j
@Service
public class TaskExecutionService {

	protected final static int DEFAULT_TASK_THREAD_NUMBER = 10;

	public static long spotcheckStartTime = 5 * 60 * 1000L;

	// 任务调度池
	private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(DEFAULT_TASK_THREAD_NUMBER);
	// 任务队列
	private BlockingQueue<SpotCheckTask> taskQueue = new LinkedBlockingQueue<SpotCheckTask>();
	// 执行结果队列
	// private BlockingQueue<Future<Task>> resultQueue = new
	// LinkedBlockingQueue<Future<Task>>();
	private Map<SpotCheckTask, Future<SpotCheckTask>> resultMap = new ConcurrentHashMap<SpotCheckTask, Future<SpotCheckTask>>();

	@Autowired
	private ISpotCheckTaskService spotCheckTaskService;
	@Autowired
	private DoTaskService doTaskService;

	public TaskExecutionService() {
		log.info("创建任务执行服务类");
	}

	@PostConstruct
	public void init() {
		log.info("扫描任务等待队列");
		CheckTaskThead checkTaskThead = new CheckTaskThead();
		checkTaskThead.start();
		log.info("处理任务执行结果队列");
		CheckResultThread checkResultThread = new CheckResultThread();
		checkResultThread.start();
		try {
			log.info("没有处理完的任务加入队列");
			List<SpotCheckTask> tasks = spotCheckTaskService.getUnderSpotCheckTasks();
			for (SpotCheckTask task : tasks) {
				addTask(task);
			}
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
	}

	public void addTask(SpotCheckTask task) throws InterruptedException {
		taskQueue.put(task);
	}

	/**
	 * 扫描需要处理的任务线程
	 */
	private class CheckTaskThead extends Thread {
		@Override
		public void run() {
			try {
				SpotCheckTask task = taskQueue.take();
				while (task != null) {
					long delay = 0L;
					// 中断任务的恢复, 计算间隔加入任务队列
					if (task.getTaskState() == TaskState.DOING) {
						delay = (task.getStartTime() //
								+ task.getUpdateTime().getTime()//
								- Calendar.getInstance().getTimeInMillis()) / 1000L;
					}
					if (delay < 0L) {
						delay = 0L;
					}
					Future<SpotCheckTask> future = executorService.schedule(new DoTask(task, doTaskService), delay,
							TimeUnit.SECONDS);
					resultMap.put(task, future);
					// resultQueue.put(future);
					// 获取下一个任务
					task = taskQueue.take();
				}
			} catch (InterruptedException e) {
				log.error(e.toString());
				e.printStackTrace();
			}

		}
	}

	/**
	 * 扫描处理结果的任务线程
	 */
	private class CheckResultThread extends Thread {
		@Override
		public void run() {
			try {
				while (true) {
					Future<SpotCheckTask> future = null;
					Set<SpotCheckTask> keySet = resultMap.keySet();
					for (SpotCheckTask task : keySet) {
						future = resultMap.get(task);
						if (future.isDone() && (task.getTaskState() == TaskState.TODO //
								|| task.getTaskState() == TaskState.DOING)) {
							// 需要再次加入等待队列
							taskQueue.put(task);
							resultMap.remove(task);
						}
					}
					Thread.sleep(1 * 60 * 1000L);
				}

			} catch (Exception e) {
				log.error(e.toString());
				e.printStackTrace();
			}
		}
	}

	public long getSpotcheckStartTime() {
		return spotcheckStartTime;
	}

	public void setSpotcheckStartTime(long spotcheckStartTime) {
		TaskExecutionService.spotcheckStartTime = spotcheckStartTime;
	}



}
