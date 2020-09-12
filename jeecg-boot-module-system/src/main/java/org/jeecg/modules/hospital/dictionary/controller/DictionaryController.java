package org.jeecg.modules.hospital.dictionary.controller;

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
import org.jeecg.modules.hospital.dictionary.entity.Dictionary;
import org.jeecg.modules.hospital.dictionary.service.IDictionaryService;
import org.jeecg.modules.hospital.dictionary.vo.DictionaryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Description: 字典表
 * @Author: jeecg-boot
 * @Date:   2020-08-25
 * @Version: V1.0
 */
@Api(tags="字典表")
@RestController
@RequestMapping("/hospital/dictionary")
@Slf4j
public class DictionaryController extends JeecgController<Dictionary, IDictionaryService> {
	@Autowired
	private IDictionaryService dictionaryService;
	
	/**
	 * 分页列表查询
	 *
	 * @param dictionary
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "字典表-分页列表查询")
	@ApiOperation(value="字典表-分页列表查询", notes="字典表-分页列表查询")
	@GetMapping(value = "/list/page")
	public Result<?> queryPageList(Dictionary dictionary,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Dictionary> queryWrapper = QueryGenerator.initQueryWrapper(dictionary, req.getParameterMap());
		Page<Dictionary> page = new Page<Dictionary>(pageNo, pageSize);
		IPage<Dictionary> pageList = dictionaryService.page(page, queryWrapper);
		return Result.ok(pageList);
	}


	/**
	 * 根据token查询用户关联的科室
	 *
	 * @param token
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "字典表-分页列表查询")
	@ApiOperation(value="字典表-分页列表查询", notes="字典表-分页列表查询")
	@GetMapping(value = "/depart/list/page")
	public Result<?> queryDepartByToken(String token,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		HashMap hashMap = new HashMap();
		hashMap.put("token",token);
		hashMap.put("pageSize",pageSize);
		hashMap.put("startIndex",(pageNo-1)*pageSize);
		Page<HashMap> page = new Page<HashMap>(pageNo, pageSize);
		List<DictionaryVo> pageList = dictionaryService.queryDepartByToken(hashMap);
		Integer count = dictionaryService.queryDepartByTokenCount(token);
		page.setSize(pageSize);
		page.setCurrent(pageNo);
		page.setTotal(count);
		if(count != null && count!=0) {
			page.setPages(count%pageSize==0?count%pageSize:count%pageSize+1);
		}else{
			page.setPages(1L);

		}
		System.out.println(pageList);
		return Result.ok(pageList);
	}


	@AutoLog(value = "字典表-分页列表查询")
	@ApiOperation(value="字典表-分页列表查询", notes="字典表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryList() {
		//初始化科室
		QueryWrapper<Dictionary> dictQueryWrapper = new QueryWrapper<>();
		dictQueryWrapper.eq("status","1").eq("superid","1");
		List<Dictionary> list = dictionaryService.list(dictQueryWrapper);

		return Result.ok(list);
	}

	/**
	 *   添加
	 *
	 * @param dictionary
	 * @return
	 */
	@AutoLog(value = "字典表-添加")
	@ApiOperation(value="字典表-添加", notes="字典表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Dictionary dictionary) {
		dictionaryService.save(dictionary);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param dictionary
	 * @return
	 */
	@AutoLog(value = "字典表-编辑")
	@ApiOperation(value="字典表-编辑", notes="字典表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Dictionary dictionary) {
		dictionaryService.updateById(dictionary);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "字典表-通过id删除")
	@ApiOperation(value="字典表-通过id删除", notes="字典表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dictionaryService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "字典表-批量删除")
	@ApiOperation(value="字典表-批量删除", notes="字典表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dictionaryService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "字典表-通过id查询")
	@ApiOperation(value="字典表-通过id查询", notes="字典表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Dictionary dictionary = dictionaryService.getById(id);
		if(dictionary==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(dictionary);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param dictionary
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Dictionary dictionary) {
        return super.exportXls(request, dictionary, Dictionary.class, "字典表");
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
        return super.importExcel(request, response, Dictionary.class);
    }

}
