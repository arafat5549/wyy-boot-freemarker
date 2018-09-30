package com.jqmkj.java.modules.sys.web;

import com.jqmkj.java.common.security.SecurityUtil;
import com.jqmkj.java.modules.sys.domain.Dict;
import com.jqmkj.java.modules.sys.service.DictService;
import com.jqmkj.java.util.DictUtil;
import com.jqmkj.java.util.JsonUtil;
import com.jqmkj.java.util.PublicUtil;
import com.jqmkj.java.util.StringUtil;
import com.jqmkj.java.util.base.Reflections;
import com.jqmkj.java.util.domain.Globals;
import com.jqmkj.java.util.domain.PageModel;
import com.jqmkj.java.util.exception.RuntimeMsgException;
import com.jqmkj.java.vo.base.SelectResult;
import com.jqmkj.java.vo.sys.DictVo;
import com.jqmkj.java.vo.sys.query.DictQuery;
import com.jqmkj.java.vo.sys.query.DictQuerySearch;
import com.jqmkj.java.vo.sys.query.DictTreeQuery;
import com.jqmkj.java.vo.sys.query.DictTreeResult;
import com.jqmkj.java.web.rest.ResultBuilder;
import com.jqmkj.java.web.rest.base.TreeVoResource;
import com.alibaba.fastjson.JSON;
import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * REST controller for managing Station.
 *
 * @author wyy
 */
@Controller
@RequestMapping("${jqmkj.adminPath}/sys/dict")
public class DictResource extends TreeVoResource<DictService, DictVo> {

    public DictResource(DictService service) {
        super(service);
    }


    @GetMapping(value = "findTreeData")
    public ResponseEntity findTreeData(DictTreeQuery dictTreeQuery) {
        List<DictTreeResult> rs = service.findTreeData(dictTreeQuery, DictUtil.getDictList());
        return ResultBuilder.buildOk(rs);
    }

    @GetMapping(value = "findSelectData")
    public ResponseEntity findSelectData(DictQuerySearch dictQuerySearch) {
        Map<String, Object> map = Maps.newHashMap();
        if (PublicUtil.isNotEmpty(dictQuerySearch.getDictQueries())) {
            List<DictQuery> dictQueries = JSON.parseArray(dictQuerySearch.getDictQueries(), DictQuery.class);
            dictQueries.forEach(dictQuery -> map.put(StringUtil.toCamelCase(dictQuery.getCode()),
                    DictUtil.getDictList(dictQuery).
                    stream().map(item -> new SelectResult(item.getVal(), item.getName())).collect(Collectors.toList())));
        }
        return ResultBuilder.buildOk(map);
    }


    @GetMapping(value = "/")
    @Timed
    public String list() {
        return "modules/sys/dictList";
    }

    /**
     * @param pm
     * @return
     * @throws URISyntaxException
     */
    @GetMapping(value = "/page")
    public ResponseEntity getPage(PageModel<Dict> pm) {
        pm.setSortDefaultName(Direction.DESC, Dict.F_SORT, Dict.F_LASTMODIFIEDDATE);
        service.findPage(pm);
        JSON rs = JsonUtil.getInstance().setRecurrenceStr("parent_name").toJsonObject(pm);
        return ResultBuilder.buildObject(rs);
    }

    @GetMapping(value = "/edit")
    @Timed
    public String form(DictVo dictVo) {
        if (dictVo == null) {
            throw new RuntimeMsgException("无法获取字典数据");
        }
        if (PublicUtil.isNotEmpty(dictVo.getParentId())) {
            service.findOptionalTopByParentId(dictVo.getParentId()).ifPresent(item ->
            {
                if(PublicUtil.isEmpty(dictVo.getId()))dictVo.setSort(item.getSort() + 30);
            });
            service.findOneById(dictVo.getParentId()).ifPresent(item -> dictVo.setParentName(item.getName()));
        }
        if (dictVo.getSort() == null) {
            dictVo.setSort(30);
        }
        return "modules/sys/dictForm";
    }

    /**
     * @param dictVo
     * @return
     * @throws URISyntaxException
     */
    @PostMapping(value = "/edit", produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity save(@Valid @RequestBody DictVo dictVo)
            throws URISyntaxException {
        log.debug("REST request to save Dict : {}", dictVo);
        // Lowercase the dictVo login before comparing with database
        if (!checkByProperty(Reflections.createObj(DictVo.class, Lists.newArrayList(DictVo.F_ID, DictVo.F_CODE),
                dictVo.getId(), dictVo.getCode()))) {
            throw new RuntimeMsgException("编码已存在");
        }
        service.save(dictVo);
        DictUtil.clearCache();
        return ResultBuilder.buildOk("保存", dictVo.getName(), "成功");
    }

    /**
     * @param ids
     * @return
     */
    @PostMapping(value = "/delete/{ids:" + Globals.LOGIN_REGEX
            + "}")
    @Timed
    public ResponseEntity delete(@PathVariable String ids) {
        log.debug("REST request to delete Dict: {}", ids);
        service.deleteByParentIds(Lists.newArrayList(ids.split(StringUtil.SPLIT_DEFAULT)), SecurityUtil.getCurrentUserId());
        DictUtil.clearCache();
        return ResultBuilder.buildOk("删除成功");
    }


    @PostMapping(value = "/lock/{ids:" + Globals.LOGIN_REGEX
            + "}")
    @Timed
    public ResponseEntity lockOrUnLock(@PathVariable String ids) {
        log.debug("REST request to lockOrUnLock User: {}", ids);
        service.lockOrUnLockByParentIds(Lists.newArrayList(ids.split(StringUtil.SPLIT_DEFAULT)), SecurityUtil.getCurrentUserId());
        DictUtil.clearCache();
        return ResultBuilder.buildOk("操作成功");
    }

}
