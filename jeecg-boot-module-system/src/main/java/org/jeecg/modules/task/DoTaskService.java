package org.jeecg.modules.task;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.hospital.monitor.entity.Hospitalmonitor;
import org.jeecg.modules.hospital.monitor.service.IHospitalmonitorService;
import org.jeecg.modules.hospital.monitors.entity.MonitorList;
import org.jeecg.modules.hospital.monitors.service.IMonitorListService;
import org.jeecg.modules.hospital.spotchecktask.entity.SpotCheckTask;
import org.jeecg.modules.hospital.spotchecktask.service.ISpotCheckTaskService;
import org.jeecg.modules.hospital.utils.TaskState;
import org.jeecg.modules.hospital.utils.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Slf4j
@Service
public class DoTaskService  {


	@Autowired
	private ISpotCheckTaskService spotCheckTaskService;

	@Autowired
	private IMonitorListService monitorListService;
	@Autowired
	private IHospitalmonitorService hospitalmonitorService;



	/**
	 * 处理定时任务
	 */
	public SpotCheckTask processTask(SpotCheckTask task) throws Exception {
		try {
			// 处理定时任务
			if (task.getTaskType() == TaskType.END_SPOTCHECK_HOSPITAL) {
				return endSpotCheckhospital(task);
			}

		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return finishTask(task);
	}


	/**
	 * 结束在线抽查
	 * @param task
	 * @return
	 * @throws Exception
	 */
	private SpotCheckTask endSpotCheckhospital(SpotCheckTask task) throws Exception {
		SpotCheckTask spotCheckTask = spotCheckTaskService.selectAllById(task.getId());
		if (spotCheckTask==null //
				|| spotCheckTask.getTaskState() == TaskState.CANCELLED
				|| spotCheckTask.getTaskState() == TaskState.DONE) {
			log.info("不存在或者已取消或者已执行完毕");
			finishTask(spotCheckTask);
		}
		// 终止调查关闭任务
		initCheckTack(spotCheckTask);
		return finishTask(task);
	}

	private void initCheckTack(SpotCheckTask spotCheckTask) {
		Hospitalmonitor hospitalmonitor = spotCheckTask.getHospitalmonitor();
		if(hospitalmonitor==null){
			return;
		}
		if ("0".equals(hospitalmonitor.getExtractstatus()) //
				|| "2".equals(hospitalmonitor.getOutstatus())) {
			// 非抽查或者已出院
			return;
		}
		// hospnum表示未上次次数
		Integer undo = monitorListService.getUndoMonitorListCount(hospitalmonitor.getId());
		hospitalmonitor.setExtractstatus("0");
		hospitalmonitor.setHospnum(undo);
		UpdateWrapper<Hospitalmonitor> updateWrapper = new UpdateWrapper<>();
		updateWrapper.set("extractstatus",0);
		updateWrapper.set("hospnum",undo);
		updateWrapper.eq("id",hospitalmonitor.getId());
		hospitalmonitorService.update(updateWrapper);

		// MonitorList是否存在在院抽查情况
		boolean existed = hospitalmonitorService.CheckHmIdWithLicit(hospitalmonitor.getId(), "04");
		if (existed) {
			return;
		}

		// 半夜11点半执行，总结一下当天上传情况
		MonitorList monitorList = new MonitorList();
		if(hospitalmonitor.getId()!=null)
		monitorList.setHmid(Integer.parseInt(hospitalmonitor.getId()));
		monitorList.setType("04");
		monitorList.setMonitdate(Calendar.getInstance().getTime()); //当天0点时间
		monitorList.setHospstatus("0");
		monitorList.setInid(0);
		monitorList.setStatus("1");
		monitorList.setCheckstatus("0");
		monitorList.setOperator("System");
		monitorList.setModifydate(Calendar.getInstance().getTime()); //实际写入时间
		monitorListService.save(monitorList);
	}


	private SpotCheckTask finishTask(SpotCheckTask task) {
		task.setTaskState(TaskState.DONE);
		spotCheckTaskService.saveOrUpdate(task);
		return task;
	}

}
