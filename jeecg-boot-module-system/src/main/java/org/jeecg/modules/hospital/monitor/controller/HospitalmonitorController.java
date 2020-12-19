package org.jeecg.modules.hospital.monitor.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.modules.hospital.dictionary.service.IDictionaryService;
import org.jeecg.modules.hospital.hisinfo.entity.Hisinfo;
import org.jeecg.modules.hospital.hisinfo.service.IHisinfoService;
import org.jeecg.modules.hospital.hisuserinfo.entity.HisUserInfo;
import org.jeecg.modules.hospital.hisuserinfo.service.IHisUserInfoService;
import org.jeecg.modules.hospital.monitor.entity.Hospitalmonitor;
import org.jeecg.modules.hospital.monitor.service.IHospitalmonitorService;
import org.jeecg.modules.hospital.monitor.vo.HospitalMonitorVo;
import org.jeecg.modules.hospital.monitor.vo.PatientBedVo;
import org.jeecg.modules.hospital.perinfo.entity.PerInfo;
import org.jeecg.modules.hospital.perinfo.service.IPerInfoService;
import org.jeecg.modules.hospital.utils.Tools;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 医院患者服务表
 * @Author: jeecg-boot
 * @Date:   2020-08-23
 * @Version: V1.0
 */
@Api(tags="医院患者服务表")
@RestController
@RequestMapping("/hospital/monitor")
@Slf4j
public class HospitalmonitorController extends JeecgController<Hospitalmonitor, IHospitalmonitorService> {
	@Autowired
	private IHospitalmonitorService hospitalmonitorService;
	@Autowired
	private IDictionaryService dictionaryService;
	@Autowired
	private IHisinfoService hisinfoService;
	@Autowired
	private IHisUserInfoService hisUserInfoService;
	@Autowired
	private IPerInfoService perInfoService;


	/**
	 * 额外一个查询导出excel
	 */
	@GetMapping(value = "/bed/list")
	public Result<?> queryPageBedsList(
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		Page page = new Page();
		HashMap parameterMap = Tools.parameterToMap(req);
		if(parameterMap.get("column")!=null){
			String column = parameterMap.get("column").toString().replace("hospitalName", "b.name").replace("hospitalCode","c.name");
			parameterMap.put("column",column);
		}
		Integer count = hospitalmonitorService.patientBedPercentageCount(parameterMap);
		List pageList = hospitalmonitorService.patientBedPercentageLists(parameterMap);
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
	 * 分页列表查询
	 *
	 * @param hospitalmonitor
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "医院患者服务表-分页列表查询")
	@ApiOperation(value="医院患者服务表-分页列表查询", notes="医院患者服务表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		Page page = new Page();
		HashMap parameterMap = Tools.parameterToMap(req);
		Integer count = hospitalmonitorService.monitorRelatedListsCount(parameterMap);
		List pageList = hospitalmonitorService.monitorRelatedLists(parameterMap);
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
	 *   添加
	 *
	 * @param hospitalmonitor
	 * @return
	 */
	@AutoLog(value = "医院患者服务表-添加")
	@ApiOperation(value="医院患者服务表-添加", notes="医院患者服务表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Hospitalmonitor hospitalmonitor) {
		hospitalmonitorService.save(hospitalmonitor);
		return Result.ok("添加成功！");
	}

	/**
	 *  编辑  行为认证 打补丁
	 * 人脸认证成功修改状态
	 * @param hospitalmonitor
	 * @return
	 */
	@ApiOperation(value="医院患者服务表-编辑", notes="医院患者服务表-编辑")
	@PostMapping(value = "/edit")
	public Result<?> edit(Hospitalmonitor hospitalmonitor) {
		//如果是0的话，标识为认证失败不做任何操作
		if("0".equals(hospitalmonitor.getType())){
			return Result.error(0,"认证失败");
		}
		Map map = hospitalmonitorService.queryByPerCode(hospitalmonitor);
		UpdateWrapper<Hospitalmonitor> update = new UpdateWrapper<>();
		if(map != null && map.get("id")!=null){
			update.eq("id",map.get("id"));
			update.set("monitorstatus",1);
			update.set("lifestatus",1);
		}else{
			Result.error(-2,"已出院");
		}
		hospitalmonitorService.update(update);
		return Result.ok("编辑成功!");
	}

	@GetMapping(value = "/detail")
	public HospitalMonitorVo detail(Hospitalmonitor hospitalmonitor) {
		return hospitalmonitorService.queryByid(hospitalmonitor);
	}


	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "医院患者服务表-通过id删除")
	@ApiOperation(value="医院患者服务表-通过id删除", notes="医院患者服务表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		hospitalmonitorService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "医院患者服务表-批量删除")
	@ApiOperation(value="医院患者服务表-批量删除", notes="医院患者服务表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.hospitalmonitorService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "医院患者服务表-通过id查询")
	@ApiOperation(value="医院患者服务表-通过id查询", notes="医院患者服务表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Hospitalmonitor hospitalmonitor = hospitalmonitorService.getById(id);
		if(hospitalmonitor==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(hospitalmonitor);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param hospitalmonitor
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Hospitalmonitor hospitalmonitor) {
        return super.exportXls(request, hospitalmonitor, Hospitalmonitor.class, "医院患者服务表");
    }

	@RequestMapping(value = "/exportXls2")
	public ModelAndView exportXls2(HttpServletRequest request, Hisinfo hisinfo) {
    	HashMap map = new HashMap();
    	if(hisinfo!=null){
    		if(hisinfo.getName()!=null){
				map.put("name",hisinfo.getName());
			}
    		if(hisinfo.getCode()!=null){
    			map.put("code",hisinfo.getCode());
			}
		}
			List pageList = hospitalmonitorService.patientBedPercentageLists(map);
		ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		mv.addObject(NormalExcelConstants.FILE_NAME, "医院床位占比"); //此处设置的filename无效 ,前端会重更新设置一下
		mv.addObject(NormalExcelConstants.CLASS, PatientBedVo.class);
		mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("医院床位占比" + "报表", "导出人:管理员"));
		mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
		return mv;	}



	/**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Hospitalmonitor.class);
    }

	@ApiOperation(value="医院患者服务表-根据身份证号查询", notes="医院患者服务表-根据身份证号查询")
	@GetMapping(value = "/percode")
	public Result<?> add(String percode) {
		QueryWrapper<Hospitalmonitor> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("percode",percode);
		queryWrapper.eq("outstatus",1);
		queryWrapper.eq("status",1);
		queryWrapper.eq("type","01");

		PerInfo perInfo = perInfoService.selectOne(percode);
		Hospitalmonitor one = hospitalmonitorService.getOne(queryWrapper);
		if(perInfo!=null && perInfo.getImage()!=null && one !=null)
		one.setImage(perInfo.getImage());
		return Result.ok(one);
	}

	@ApiOperation(value="医院患者服务表-更新", notes="医院患者服务表-更新")
	@PostMapping(value = "/update")
	public Result<?> update(@RequestBody Hospitalmonitor hospitalmonitor) {
		UpdateWrapper updateWrapper = new UpdateWrapper();
		updateWrapper.eq("id",hospitalmonitor.getId());
		if(hospitalmonitor.getInscode() != null){
			updateWrapper.set("inscode",hospitalmonitor.getInscode());
		}
		if(hospitalmonitor.getPhone() != null){
			updateWrapper.set("phone",hospitalmonitor.getPhone());
		}
		if(hospitalmonitor.getDept() != null){
			updateWrapper.set("dept",hospitalmonitor.getDept());
		}
		if(hospitalmonitor.getDiagnose() != null){
			updateWrapper.set("diagnose",hospitalmonitor.getDiagnose());
		}
		if(hospitalmonitor.getIncode()!= null){
			updateWrapper.set("incode",hospitalmonitor.getIncode());
		}
		if(hospitalmonitor.getWardcode() != null){
			updateWrapper.set("wardcode",hospitalmonitor.getWardcode());
		}
		if(hospitalmonitor.getWardcode() != null || hospitalmonitor.getIncode()!= null || hospitalmonitor.getDiagnose() != null || hospitalmonitor.getDept() != null ||hospitalmonitor.getPhone() != null || hospitalmonitor.getInscode() != null)
		hospitalmonitorService.update(updateWrapper);
		if(hospitalmonitor != null && hospitalmonitor.getId()!=null && hospitalmonitor.getImage()!=null)
		perInfoService.updatePerInfoByCode(hospitalmonitor);

		return Result.ok("修改成功!");
	}


	@ApiOperation(value="医院患者服务表-根据身份证号查询", notes="医院患者服务表-根据身份证号查询")
	@GetMapping(value = "/percode/nine")
	public Result<?> getHospitalMonitorByPerCode(Hospitalmonitor hospitalmonitor, @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
												 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {
		//校验token
		if(hospitalmonitor.getToken() !=null) {
			HisUserInfo hisUserInfo = hisUserInfoService.selectByPercode(hospitalmonitor.getToken());
			if(hisUserInfo==null){
				return Result.error(-3,"登录失效");
			}
		}else{
			return Result.error(-3,"登录失效");
		}
		Page<Hospitalmonitor> page = new Page<Hospitalmonitor>(pageNo, pageSize);
		List<Hospitalmonitor> hospitalmonitors = hospitalmonitorService.selectHospitalMonitorByPerCode(hospitalmonitor, pageNo, pageSize);
		Integer count = hospitalmonitorService.selectHospitalMonitorByPerCodeCount(hospitalmonitor);
		page.setTotal(count);
		if(count != null && count!=0) {
			page.setPages(count%pageSize==0?count%pageSize:count%pageSize+1);
		}else{
			page.setPages(1L);

		}
		page.setRecords(hospitalmonitors);
		return Result.ok(page);
	}
}
