package org.jeecg.modules.hospital.hisinfo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.hospital.hisinfo.entity.Hisinfo;
import org.jeecg.modules.hospital.hisinfo.service.IHisinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

 /**
 * @Description: 医院表
 * @Author: jeecg-boot
 * @Date:   2020-08-25
 * @Version: V1.0
 */
@Api(tags="医院表")
@RestController
@RequestMapping("/hospital/hisinfo")
@Slf4j
public class HisinfoController extends JeecgController<Hisinfo, IHisinfoService> {
	@Autowired
	private IHisinfoService hisinfoService;
	
	/**
	 * 分页列表查询
	 *
	 * @param hisinfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "医院表-分页列表查询")
	@ApiOperation(value="医院表-分页列表查询", notes="医院表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Hisinfo hisinfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Hisinfo> queryWrapper = QueryGenerator.initQueryWrapper(hisinfo, req.getParameterMap());
		Page<Hisinfo> page = new Page<Hisinfo>(pageNo, pageSize);
		IPage<Hisinfo> pageList = hisinfoService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param hisinfo
	 * @return
	 */
	@AutoLog(value = "医院表-添加")
	@ApiOperation(value="医院表-添加", notes="医院表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Hisinfo hisinfo) {
		hisinfoService.save(hisinfo);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param hisinfo
	 * @return
	 */
	@AutoLog(value = "医院表-编辑")
	@ApiOperation(value="医院表-编辑", notes="医院表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Hisinfo hisinfo) {
		hisinfoService.updateById(hisinfo);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "医院表-通过id删除")
	@ApiOperation(value="医院表-通过id删除", notes="医院表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		hisinfoService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "医院表-批量删除")
	@ApiOperation(value="医院表-批量删除", notes="医院表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.hisinfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "医院表-通过id查询")
	@ApiOperation(value="医院表-通过id查询", notes="医院表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Hisinfo hisinfo = hisinfoService.getById(id);
		if(hisinfo==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(hisinfo);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param hisinfo
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Hisinfo hisinfo) {
        return super.exportXls(request, hisinfo, Hisinfo.class, "医院表");
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
        return super.importExcel(request, response, Hisinfo.class);
    }

}
