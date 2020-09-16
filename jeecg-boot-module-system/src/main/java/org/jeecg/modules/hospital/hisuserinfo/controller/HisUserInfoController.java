package org.jeecg.modules.hospital.hisuserinfo.controller;

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
import org.jeecg.modules.hospital.hisuserinfo.entity.HisUserInfo;
import org.jeecg.modules.hospital.hisuserinfo.service.IHisUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 登陆用户表
 * @Author: jeecg-boot
 * @Date:   2020-08-29
 * @Version: V1.0
 */
@Api(tags="登陆用户表")
@RestController
@RequestMapping("/hospital/hisUserInfo")
@Slf4j
public class HisUserInfoController extends JeecgController<HisUserInfo, IHisUserInfoService> {
	@Autowired
	private IHisUserInfoService hisUserInfoService;




	 /**
	  * 根据token查询用户
	  *
	  * @return
	  */
	 @AutoLog(value = "登陆用户表-分页列表查询")
	 @ApiOperation(value="登陆用户表-分页列表查询", notes="登陆用户表-分页列表查询")
	 @GetMapping(value = "/user/token")
	 public Result<?> queryHisUserInfoByToken(@RequestParam String token) {
		 QueryWrapper<HisUserInfo> queryWrapper = new QueryWrapper();
		 queryWrapper.eq("token",token);
		 List<HisUserInfo> list = hisUserInfoService.list(queryWrapper);
		 return Result.ok(list!=null&&list.size()>0?list.get(0):"");
	 }

	/**
	 * 分页列表查询
	 *
	 * @param hisUserInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "登陆用户表-分页列表查询")
	@ApiOperation(value="登陆用户表-分页列表查询", notes="登陆用户表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(HisUserInfo hisUserInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<HisUserInfo> queryWrapper = QueryGenerator.initQueryWrapper(hisUserInfo, req.getParameterMap());
		Page<HisUserInfo> page = new Page<HisUserInfo>(pageNo, pageSize);
		IPage<HisUserInfo> pageList = hisUserInfoService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param hisUserInfo
	 * @return
	 */
	@AutoLog(value = "登陆用户表-添加")
	@ApiOperation(value="登陆用户表-添加", notes="登陆用户表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody HisUserInfo hisUserInfo) {
		hisUserInfoService.save(hisUserInfo);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param hisUserInfo
	 * @return
	 */
	@AutoLog(value = "登陆用户表-编辑")
	@ApiOperation(value="登陆用户表-编辑", notes="登陆用户表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody HisUserInfo hisUserInfo) {
		hisUserInfoService.updateById(hisUserInfo);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "登陆用户表-通过id删除")
	@ApiOperation(value="登陆用户表-通过id删除", notes="登陆用户表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		hisUserInfoService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "登陆用户表-批量删除")
	@ApiOperation(value="登陆用户表-批量删除", notes="登陆用户表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.hisUserInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "登陆用户表-通过id查询")
	@ApiOperation(value="登陆用户表-通过id查询", notes="登陆用户表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		HisUserInfo hisUserInfo = hisUserInfoService.getById(id);
		if(hisUserInfo==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(hisUserInfo);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param hisUserInfo
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HisUserInfo hisUserInfo) {
        return super.exportXls(request, hisUserInfo, HisUserInfo.class, "登陆用户表");
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
        return super.importExcel(request, response, HisUserInfo.class);
    }


	@AutoLog(value = "登陆用户表-分页列表查询")
	@ApiOperation(value="登陆用户表-分页列表查询", notes="登陆用户表-分页列表查询")
	@GetMapping(value = "/user/code")
	public Result<?> queryHisUserInfoByCode(@RequestParam String code) {
		QueryWrapper<HisUserInfo> queryWrapper = new QueryWrapper();
		queryWrapper.eq("code",code);
		List<HisUserInfo> list = hisUserInfoService.list(queryWrapper);
		return Result.ok(list!=null&&list.size()>0?list.get(0):"");
	}
}
