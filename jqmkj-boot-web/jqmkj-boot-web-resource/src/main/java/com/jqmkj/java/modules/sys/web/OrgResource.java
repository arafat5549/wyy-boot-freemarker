package com.jqmkj.java.modules.sys.web;

import com.jqmkj.java.common.security.SecurityUtil;
import com.jqmkj.java.modules.sys.domain.Org;
import com.jqmkj.java.modules.sys.service.OrgService;
import com.jqmkj.java.util.JsonUtil;
import com.jqmkj.java.util.PublicUtil;
import com.jqmkj.java.util.StringUtil;
import com.jqmkj.java.util.base.Reflections;
import com.jqmkj.java.util.domain.Globals;
import com.jqmkj.java.util.domain.PageModel;
import com.jqmkj.java.util.exception.RuntimeMsgException;
import com.jqmkj.java.vo.sys.OrgVo;
import com.jqmkj.java.vo.sys.query.OrgTreeQuery;
import com.jqmkj.java.vo.sys.query.TreeResult;
import com.jqmkj.java.web.rest.ResultBuilder;
import com.jqmkj.java.web.rest.base.TreeVoResource;
import com.alibaba.fastjson.JSON;
import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.Lists;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * REST controller for managing Station.
 *
 * @author wyy
 */
@Controller
@RequestMapping("${jqmkj.adminPath}/sys/org")
public class OrgResource extends TreeVoResource<OrgService, OrgVo> {

    public OrgResource(OrgService service) {
        super(service);
    }

    @GetMapping(value = "findTreeData")
    public ResponseEntity findTreeData(OrgTreeQuery orgTreeQuery) {
        List<TreeResult> treeResultList = service.findTreeData(orgTreeQuery, SecurityUtil.getOrgList());
        return ResultBuilder.buildOk(treeResultList);
    }

    @GetMapping(value = "/")
    @Timed
    public String list() {
        return "modules/sys/orgList";
    }

    /**
     * @param pm
     * @return
     */
    @GetMapping(value = "/page")
    public ResponseEntity getPage(PageModel pm) {
        pm.setSortDefaultName(Direction.DESC, Org.F_LASTMODIFIEDDATE);
        service.findPage(pm, SecurityUtil.dataScopeFilter(
                SecurityUtil.getCurrentUserId(), "this", ""));
        JSON json = JsonUtil.getInstance().toJsonObject(pm);
        return ResultBuilder.buildObject(json);
    }

    @GetMapping(value = "/edit")
    @Timed
    public String form(OrgVo orgVo) {
        if (orgVo == null) {
            throw new RuntimeMsgException(PublicUtil.toAppendStr("查询模块管理失败，原因：无法查找到编号区域"));
        }
        if (PublicUtil.isNotEmpty(orgVo.getParentId())) {
            service.findOptionalTopByParentId(orgVo.getParentId()).ifPresent(item -> {
                if(PublicUtil.isEmpty(orgVo.getId()))orgVo.setSort(item.getSort() + 30);
            });
            service.findOneById(orgVo.getParentId()).ifPresent(item -> orgVo.setParentName(item.getName()));
        }
        if (orgVo.getSort() == null) {
            orgVo.setSort(30);
        }
        return "modules/sys/orgForm";
    }

    /**
     * @param orgVo
     * @return
     */
    @PostMapping(value = "/edit", produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity save(@Valid @RequestBody OrgVo orgVo) {
        log.debug("REST request to save orgVo : {}", orgVo);
        // Lowercase the org login before comparing with database
        if (!checkByProperty(Reflections.createObj(OrgVo.class, Lists.newArrayList(OrgVo.F_ID, OrgVo.F_NAME),
                orgVo.getId(), orgVo.getName()))) {
            throw new RuntimeMsgException("名称已存在");
        }
        service.save(orgVo);
        SecurityUtil.clearUserJedisCache();
        return ResultBuilder.buildOk("保存", orgVo.getName(), "成功");
    }

    /**
     * @param ids
     * @return
     */
    @PostMapping(value = "/delete/{ids:" + Globals.LOGIN_REGEX
            + "}")
    @Timed
    public ResponseEntity delete(@PathVariable String ids) {
        log.debug("REST request to delete Org: {}", ids);
        service.deleteByParentIds(Lists.newArrayList(ids.split(StringUtil.SPLIT_DEFAULT)), SecurityUtil.getCurrentUserId());
        SecurityUtil.clearUserJedisCache();
        return ResultBuilder.buildOk("删除成功");
    }

    /**
     * @param ids
     * @return
     */
    @PostMapping(value = "/lock/{ids:" + Globals.LOGIN_REGEX
            + "}")
    @Timed
    public ResponseEntity lockOrUnLock(@PathVariable String ids) {
        log.debug("REST request to lockOrUnLock User: {}", ids);
        service.lockOrUnLockByParentIds(Lists.newArrayList(ids.split(StringUtil.SPLIT_DEFAULT)), SecurityUtil.getCurrentUserId());
        SecurityUtil.clearUserJedisCache();
        return ResultBuilder.buildOk("操作成功");
    }

}
