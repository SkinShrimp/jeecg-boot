package org.jeecg.modules.hospital.perinfo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.hospital.monitor.entity.Hospitalmonitor;
import org.jeecg.modules.hospital.monitor.service.IHospitalmonitorService;
import org.jeecg.modules.hospital.perinfo.entity.PerInfo;
import org.jeecg.modules.hospital.perinfo.service.IPerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 住院人信息
 * @Author: jeecg-boot
 * @Date:   2020-08-29
 * @Version: V1.0
 */
@Api(tags="住院人信息")
@RestController
@RequestMapping("/hospital/perInfo")
@Slf4j
public class PerInfoController extends JeecgController<PerInfo, IPerInfoService> {
	@Autowired
	private IPerInfoService perInfoService;
	@Autowired
	private IHospitalmonitorService hospitalmonitorService;


	 /**
	  * 根据token将图片置空
	  * @return
	  */
	 @AutoLog(value = "住院人信息-分页列表查询")
	 @ApiOperation(value="住院人信息-分页列表查询", notes="住院人信息-分页列表查询")
	 @PostMapping(value = "/image/update")
	 public Result<?> imageUpdate(@RequestParam String percode) {
		 QueryWrapper<PerInfo> queryWrapper = new QueryWrapper();
		 queryWrapper.eq("percode",percode);
		 List<PerInfo> list = perInfoService.list(queryWrapper);
		 if(list != null) {
			 for (PerInfo perInfo : list) {
				 if(perInfo.getImage() != null && perInfo.getImage().equals("dlgs.png")){
					 UpdateWrapper updateWrapper = new UpdateWrapper();
					 updateWrapper.set("image",null);
					 perInfoService.update(updateWrapper);
				 }
			 }
		 }

		 QueryWrapper<Hospitalmonitor> wrapper = new QueryWrapper();
		 queryWrapper.eq("percode",percode);
		 List<Hospitalmonitor> hospitalmonitors = hospitalmonitorService.list(wrapper);
		 if(hospitalmonitors != null) {
			 for (Hospitalmonitor hospitalmonitor : hospitalmonitors) {
				 if(hospitalmonitor.getImage1() != null && hospitalmonitor.getImage1().equals("dlgs.png")){
					 UpdateWrapper updateWrapper = new UpdateWrapper();
					 updateWrapper.set("image1",null);
					 if(hospitalmonitor.getCheckstatus().equals("0")){
						 updateWrapper.set("checkstatus", 1);
					 }
					 hospitalmonitorService.update(updateWrapper);
				 }
			 }
		 }
		 return Result.ok();
	 }


	 /**
	 * 分页列表查询
	 *
	 * @param perInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "住院人信息-分页列表查询")
	@ApiOperation(value="住院人信息-分页列表查询", notes="住院人信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(PerInfo perInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<PerInfo> queryWrapper = QueryGenerator.initQueryWrapper(perInfo, req.getParameterMap());
		Page<PerInfo> page = new Page<PerInfo>(pageNo, pageSize);
		IPage<PerInfo> pageList = perInfoService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param perInfo
	 * @return
	 */
	@AutoLog(value = "住院人信息-添加")
	@ApiOperation(value="住院人信息-添加", notes="住院人信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody PerInfo perInfo) {
		perInfoService.save(perInfo);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param perInfo
	 * @return
	 */
	@AutoLog(value = "住院人信息-编辑")
	@ApiOperation(value="住院人信息-编辑", notes="住院人信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody PerInfo perInfo) {
		perInfoService.updateById(perInfo);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "住院人信息-通过id删除")
	@ApiOperation(value="住院人信息-通过id删除", notes="住院人信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		perInfoService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "住院人信息-批量删除")
	@ApiOperation(value="住院人信息-批量删除", notes="住院人信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.perInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "住院人信息-通过id查询")
	@ApiOperation(value="住院人信息-通过id查询", notes="住院人信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		PerInfo perInfo = perInfoService.getById(id);
		if(perInfo==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(perInfo);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param perInfo
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, PerInfo perInfo) {
        return super.exportXls(request, perInfo, PerInfo.class, "住院人信息");
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
        return super.importExcel(request, response, PerInfo.class);
    }

}
