package org.jeecg.modules.hospital.monitors.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.modules.hospital.monitor.entity.Hospitalmonitor;
import org.jeecg.modules.hospital.monitor.service.IHospitalmonitorService;
import org.jeecg.modules.hospital.monitors.entity.MonitorList;
import org.jeecg.modules.hospital.monitors.service.IMonitorListService;
import org.jeecg.modules.hospital.monitors.vo.MonitorListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 服务观察
 * @Author: jeecg-boot
 * @Date:   2020-08-23
 * @Version: V1.0
 */
@Api(tags="服务观察")
@RestController
@RequestMapping("/monitors")
@Slf4j
public class MonitorListController extends JeecgController<MonitorList, IMonitorListService> {
	@Autowired
	private IMonitorListService monitorListService;
	@Autowired
	private IHospitalmonitorService hospitalmonitorService;
	/**
	 * 分页列表查询
	 *
	 * @param monitorListVo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "服务观察-分页列表查询")
	@ApiOperation(value="服务观察-分页列表查询", notes="服务观察-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryList(MonitorListVo monitorListVo,
							   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
							   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
							   HttpServletRequest req) {
		Page page = new Page();
		List<MonitorListVo> listVos = monitorListService.queryList(monitorListVo);
		Integer count = monitorListService.queryListCount(monitorListVo);
		page.setSize(pageSize);
		page.setCurrent(pageNo);
		page.setTotal(count);
		if(count != null && count!=0) {
			page.setPages(count%pageSize==0?count%pageSize:count%pageSize+1);
		}else{
			page.setPages(1L);

		}
		page.setRecords(listVos);
		return Result.ok(page);
	}
	
	/**
	 *   添加
	 *
	 * @param monitorList
	 * @return
	 */
	@AutoLog(value = "服务观察-添加")
	@ApiOperation(value="服务观察-添加", notes="服务观察-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MonitorList monitorList) {
		monitorListService.save(monitorList);
		return Result.ok("添加成功！");
	}

	@PostMapping(value = "/update")
	public Result<?> update(@RequestBody MonitorList monitorList) {
		UpdateWrapper<Hospitalmonitor> update = new UpdateWrapper<Hospitalmonitor>();
		update.eq("id",monitorList.getHmid());
		if(StringUtils.isNotBlank(monitorList.getMonitorstatus())){
			update.set("monitorstatus",monitorList.getMonitorstatus());
		}
		if(StringUtils.isNotBlank(monitorList.getGpscheckstatus())){
			update.set("gpscheckstatus",monitorList.getGpscheckstatus());
		}
		if(StringUtils.isNotBlank(monitorList.getMonitorstatus()) || StringUtils.isNotBlank(monitorList.getGpscheckstatus()))
		hospitalmonitorService.update(update);
		monitorListService.updateMonitorById(monitorList);
		return Result.ok("编辑成功!");
	}

	/**
	 *  编辑
	 *
	 * @param monitorList
	 * @return
	 */
	@AutoLog(value = "服务观察-编辑")
	@ApiOperation(value="服务观察-编辑", notes="服务观察-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody MonitorList monitorList) {
		monitorListService.updateById(monitorList);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "服务观察-通过id删除")
	@ApiOperation(value="服务观察-通过id删除", notes="服务观察-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		monitorListService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "服务观察-批量删除")
	@ApiOperation(value="服务观察-批量删除", notes="服务观察-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.monitorListService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "服务观察-通过id查询")
	@ApiOperation(value="服务观察-通过id查询", notes="服务观察-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MonitorList monitorList = monitorListService.getById(id);
		if(monitorList==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(monitorList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param monitorList
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, MonitorList monitorList) {
        return super.exportXls(request, monitorList, MonitorList.class, "服务观察");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, MonitorList.class);
    }

}
