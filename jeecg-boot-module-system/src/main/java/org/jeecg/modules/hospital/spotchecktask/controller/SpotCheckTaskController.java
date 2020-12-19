package org.jeecg.modules.hospital.spotchecktask.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.modules.hospital.dictionary.entity.Dictionary;
import org.jeecg.modules.hospital.dictionary.service.IDictionaryService;
import org.jeecg.modules.hospital.hisinfo.entity.Hisinfo;
import org.jeecg.modules.hospital.hisinfo.service.IHisinfoService;
import org.jeecg.modules.hospital.monitor.entity.Hospitalmonitor;
import org.jeecg.modules.hospital.monitor.service.IHospitalmonitorService;
import org.jeecg.modules.hospital.monitors.service.IMonitorListService;
import org.jeecg.modules.hospital.spotchecktask.entity.SpotCheckTask;
import org.jeecg.modules.hospital.spotchecktask.service.ISpotCheckTaskService;
import org.jeecg.modules.hospital.spotchecktask.vo.SpotCheckTaskVo;
import org.jeecg.modules.hospital.utils.TaskState;
import org.jeecg.modules.hospital.utils.Tools;
import org.jeecg.modules.task.TaskExecutionService;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

@Api(tags="工作任务")
@RestController
@RequestMapping("/hospital/spotCheckTask")
@Slf4j
public class SpotCheckTaskController extends JeecgController<SpotCheckTask, ISpotCheckTaskService> {
    @Autowired
    private ISpotCheckTaskService spotCheckTaskService;
    @Autowired
    private IHospitalmonitorService hospitalmonitorService;
    @Autowired
    private IDictionaryService dictionaryService;
    @Autowired
    private IHisinfoService hisinfoService;
    @Autowired
    private TaskExecutionService taskExecutionService;
    @Autowired
    private IMonitorListService monitorListService;


    @GetMapping(value = "/initUser")
    public Result<?> queryPageList(String code) {

        return Result.OK();
    }

    @GetMapping(value = "/list")
    public Result<?> queryPageList(Hospitalmonitor hospitalmonitor,
                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                   HttpServletRequest req) {
        Page page = new Page();
        HashMap parameterMap = Tools.parameterToMap(req);
        Integer count = spotCheckTaskService.spotCheckTaskListsCount(parameterMap);
        List<SpotCheckTaskVo> pageList = spotCheckTaskService.spotCheckTaskLists(parameterMap);
        page.setSize(pageSize);
        page.setCurrent(pageNo);
        page.setTotal(count);
        if(count != null && count!=0) {
            page.setPages(count%pageSize==0?count%pageSize:count%pageSize+1);
        }else{
            page.setPages(1L);

        }
        page.setRecords(pageList);


        return Result.ok(page);
    }


    /**
     * 接收到在院抽查通知 25
     */
    @PostMapping(value = "/check")
    public void setSpotCheck(@RequestBody SpotCheckTaskVo spotCheckTask) {

        if(spotCheckTask!=null && spotCheckTask.getHospitalmonitors()!=null && spotCheckTask.getHospitalmonitors().size()>0) {
            for (int i = 0; i < spotCheckTask.getHmIds().size(); i++) {
                Hospitalmonitor hospitalMonitor = this.hospitalmonitorService.getById(spotCheckTask.getHmIds().get(i).toString());
                Dictionary dictionary = dictionaryService.getById(hospitalMonitor.getDept());
                hospitalMonitor.setDictionary(dictionary);
                Hisinfo hisinfo = hisinfoService.getById(hospitalMonitor.getHisid());
                hospitalMonitor.setHisinfo(hisinfo);

                if(spotCheckTask.getHospitalmonitors().get(i).getStatus() !=null &&"0".equals(spotCheckTask.getHospitalmonitors().get(i).getStatus())){
                    hospitalMonitor.setExtractstatus("1");
                }else{
                    hospitalMonitor.setExtractstatus("1");
                }

                UpdateWrapper<Hospitalmonitor> update = new UpdateWrapper<>();
                update.set("extractstatus", hospitalMonitor.getExtractstatus());
                update.eq("id",hospitalMonitor.getId());
                //抽查前更新extractstatus 为1
                this.hospitalmonitorService.update(update);
                // 处理任务
                if ("0".equals(hospitalMonitor.getExtractstatus())) {
                    // 取消抽查
                    SpotCheckTask task = spotCheckTaskService.getSpotCheckTaskByHmId(hospitalMonitor.getId());
                    if (task != null) {
                        task.setTaskState(TaskState.CANCELLED);
                        spotCheckTaskService.saveOrUpdate(task);
                    }
                } else {
                    // 新增抽查
                    SpotCheckTask task = spotCheckTaskService.getSpotCheckTaskByHmId(hospitalMonitor.getId());
                    if (task == null //
                            || task.getTaskState() == TaskState.CANCELLED //
                            || task.getTaskState() == TaskState.DONE) {
                        task = new SpotCheckTask();
                        task.setCreateTime(Calendar.getInstance().getTime());
                        if("0".equals(spotCheckTask.getCheckStatus())){
                            task.setUpdateTime(spotCheckTask.getDateStart());
                            task.setTaskState(TaskState.TODO);
                        }else {
                            task.setTaskState(TaskState.DOING);
                            task.setUpdateTime(Calendar.getInstance().getTime());
                        }
                        task.setStartTime(spotCheckTask.getDateInterval()*60*1000L);
                        task.setHmId (hospitalMonitor!=null?hospitalMonitor.getId():null);
                        task.setHospitalmonitor(hospitalMonitor);


//                        task.setTaskType(TaskType.END_SPOTCHECK_HOSPITAL);
                        try {
                            taskExecutionService.addTask(task);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        spotCheckTaskService.saveOrUpdate(task);

                    }

                }

//                List<SpotCheckTask> tasks = //
//                        spotCheckTaskService.getSpotCheckTasks(spotCheckTask);
//                for (SpotCheckTask task : tasks) {
//                    task.setUpdateTime(Calendar.getInstance().getTime());
//                    task.setStartTime(TaskExecutionService.spotcheckStartTime);
//                    task.setTaskState(TaskState.DOING);
//                    try {
//                        taskExecutionService.addTask(task);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    spotCheckTaskService.saveOrUpdate(task);
//
//                }
            }
        }
    }

    @GetMapping(value = "/getCheckUsers")
    public Result<?> getCheckUsers(Hospitalmonitor hospitalmonitor, @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                    @RequestParam(name="pageSize", defaultValue="10") Integer pageSize){
            Page page = new Page();
            QueryWrapper<SpotCheckTask> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("task_state","DOING");
            Integer count = spotCheckTaskService.selectCheckUsersCount(hospitalmonitor);
            page.setSize(pageSize);
            page.setCurrent(pageNo);
            page.setTotal(count);
            List<SpotCheckTask> spotCheckTasks = spotCheckTaskService.selectCheckUsers(hospitalmonitor,pageNo, pageSize);
            page.setRecords(spotCheckTasks);
            if(count != null && count!=0) {
                page.setPages(count%pageSize==0?count%pageSize:count%pageSize+1);
            }else{
                page.setPages(1L);

            }
            return Result.ok(page);
    }

    @PostMapping(value = "/endSpotCheck")
    public Result<?> endSpotCheckhospital(Hospitalmonitor hospitalmonitor) {
        spotCheckTaskService.updateTaskType(hospitalmonitor);
        return Result.ok("认证成功！");
    }
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Hospitalmonitor hospitalmonitor) {
        HashMap parameterMap = Tools.parameterToMap(request);
        List<SpotCheckTaskVo> pageList = spotCheckTaskService.spotCheckTaskListsAll(parameterMap);
        // Step.3 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, "抽查表"); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, SpotCheckTaskVo.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("抽查表", "导出人:管理员"));
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        return mv;


    }
}
