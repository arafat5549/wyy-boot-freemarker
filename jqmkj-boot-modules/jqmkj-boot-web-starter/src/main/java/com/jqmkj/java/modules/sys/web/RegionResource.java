/**
 * Copyright &copy; 2018 <a href="https://github.com/somewhereMrli/jqmkj-boot">jqmkj-boot</a> All rights reserved.
 */
package com.jqmkj.java.modules.sys.web;

import com.jqmkj.java.common.security.SecurityUtil;
import com.jqmkj.java.util.StringUtil;
import com.google.common.collect.Lists;
import com.jqmkj.java.modules.sys.domain.vo.RegionVo;
import com.jqmkj.java.modules.sys.service.RegionService;
import com.jqmkj.java.util.JsonUtil;
import com.jqmkj.java.util.PublicUtil;
import com.jqmkj.java.util.domain.Globals;
import com.jqmkj.java.util.domain.PageModel;
import com.jqmkj.java.util.exception.RuntimeMsgException;
import com.jqmkj.java.web.rest.ResultBuilder;
import com.jqmkj.java.web.rest.base.DataVoResource;
import com.alibaba.fastjson.JSON;
import com.codahale.metrics.annotation.Timed;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * 行政区域管理Controller 行政区域
 * @author admin
 * @version 2018-09-30
 */
@Controller
@RequestMapping(value = "${jqmkj.adminPath}/sys/region")
public class RegionResource extends DataVoResource<RegionService, RegionVo> {

   public RegionResource(RegionService regionService) {
        super(regionService);
    }
	@GetMapping(value = "/")
	@Timed
	public String list() {
		return "modules/sys/regionList";
	}

	/**
	 * GET / : get all region.
	 *
	 * @param pm
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and with body all region
	 */
	@GetMapping(value = "/page")
	@Timed
	public ResponseEntity getPage(PageModel pm) {
	    service.findPage(pm, SecurityUtil.dataScopeFilter());
		JSON json = JsonUtil.getInstance().setRecurrenceStr().toJsonObject(pm);
		return ResultBuilder.buildObject(json);
	}

	@GetMapping(value = "/edit")
	@Timed
	public String form(RegionVo regionVo) {
		if(regionVo == null){
			throw new RuntimeMsgException(PublicUtil.toAppendStr("查询行政区域失败，原因：无法查找到编号为[", request().getParameter("id"), "]的行政区域"));
		}
		return "modules/sys/regionForm";
	}

	/**
	 * POST / : Save a regionVo.
	 *
	 * @param regionVo the HTTP region
	 */
	@PostMapping(value = "/edit", produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity save(@Valid @RequestBody RegionVo regionVo) {
		log.debug("REST request to save RegionForm : {}", regionVo);
        service.checkProperty(regionVo);
		service.save(regionVo);
		return ResultBuilder.buildOk("保存行政区域成功");

	}

	/**
	 * DELETE //:id : delete the "id" Region.
	 *
	 * @param ids the id of the region to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@PostMapping(value = "/delete/{ids:" + Globals.LOGIN_REGEX
			+ "}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity delete(@PathVariable String ids) {
		log.debug("REST request to delete Region: {}", ids);
		service.deleteBatchIds(Lists.newArrayList(ids.split(StringUtil.SPLIT_DEFAULT)));
		return ResultBuilder.buildOk("删除行政区域成功");
	}
	/**
	 * lock //:id : lockOrUnLock the "id" Region.
	 *
	 * @param ids the id of the region to lock
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@PostMapping(value = "/lock/{ids:" + Globals.LOGIN_REGEX
			+ "}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity lockOrUnLock(@PathVariable String ids) {
		log.debug("REST request to lockOrUnLock Region: {}", ids);
		service.lockOrUnLock(Lists.newArrayList(ids.split(StringUtil.SPLIT_DEFAULT)));
		return ResultBuilder.buildOk("操作行政区域成功");
	}

}