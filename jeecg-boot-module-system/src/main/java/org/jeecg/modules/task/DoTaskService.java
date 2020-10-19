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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

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

			//*****************************************打补丁 当app端认证成功后，修改抽查表就好了不做后面的逻辑
			if(task!=null && task.getHmId()!=null){
				Hospitalmonitor byId = hospitalmonitorService.getById(task.getHmId());
				if(byId!=null&&byId.getExtractstatus()!=null&&"0".equals(byId.getExtractstatus())){
					UpdateWrapper update = new UpdateWrapper();
					update.eq("hm_id",byId.getId());
					update.eq("task_state",TaskState.DOING);
					update.set("task_state",TaskState.DONE);
					spotCheckTaskService.update(update);
                    return null;
				}

			}
			endSpotCheckhospital(task);

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
//		if ("0".equals(hospitalmonitor.getExtractstatus()) //
//				|| "2".equals(hospitalmonitor.getOutstatus())) {
//			// 非抽查或者已出院
//			return;
//		}
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
//		boolean existed = hospitalmonitorService.CheckHmIdWithLicit(hospitalmonitor.getId(), "04");
//		if (existed) {
//			return;
//		}

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
		monitorListService.insert(monitorList);
	}


	private SpotCheckTask finishTask(SpotCheckTask task) {
		//已经认证完成的抽查直接返回 不做修改
		if(task.getTaskState()!=null&&task.getId()!=null) {
			task = spotCheckTaskService.getById(task.getId());
		}
		if(task.getTaskState()!=null && TaskState.CANCELLED.equals(task.getTaskState())&& TaskState.CANCELLED.equals(task.getTaskState())) {
			return task;
		}
		task.setTaskState(TaskState.DONE);
		task.setUpdateTime(new Date());
		spotCheckTaskService.saveOrUpdate(task);
		return task;
	}

}
