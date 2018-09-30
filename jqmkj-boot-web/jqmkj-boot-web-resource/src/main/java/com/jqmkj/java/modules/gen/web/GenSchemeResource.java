package com.jqmkj.java.modules.gen.web;

import com.jqmkj.java.common.config.template.tag.FormDirective;
import com.jqmkj.java.common.security.AuthoritiesConstants;
import com.jqmkj.java.common.security.SecurityUtil;
import com.jqmkj.java.modules.gen.domain.GenScheme;
import com.jqmkj.java.modules.gen.domain.GenTable;
import com.jqmkj.java.modules.gen.domain.xml.GenConfig;
import com.jqmkj.java.modules.gen.service.GenSchemeService;
import com.jqmkj.java.modules.gen.service.GenTableService;
import com.jqmkj.java.modules.sys.domain.Dict;
import com.jqmkj.java.modules.sys.service.ModuleService;
import com.jqmkj.java.util.GenUtil;
import com.jqmkj.java.util.JsonUtil;
import com.jqmkj.java.util.PublicUtil;
import com.jqmkj.java.util.StringUtil;
import com.jqmkj.java.util.base.Collections3;
import com.jqmkj.java.util.domain.Globals;
import com.jqmkj.java.util.domain.PageModel;
import com.jqmkj.java.vo.gen.GenSchemeVo;
import com.jqmkj.java.vo.gen.GenTableVo;
import com.jqmkj.java.web.rest.ResultBuilder;
import com.jqmkj.java.web.rest.base.DataVoResource;
import com.alibaba.fastjson.JSON;
import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.Lists;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 生成方案Controller
 *
 * @author wyy
 */
@Controller
@RequestMapping(value = "${jqmkj.adminPath}/gen/genScheme")
public class GenSchemeResource extends DataVoResource<GenSchemeService, GenSchemeVo> {

    @Resource
    private GenTableService genTableService;

    @Resource
    private ModuleService moduleService;

    public GenSchemeResource(GenSchemeService service) {
        super(service);
    }

    @GetMapping(value = "/")
    @Timed
    public String list() {
        return "modules/gen/genSchemeList";
    }

    /**
     * @param pm
     * @return
     */
    @GetMapping(value = "/page")
    @Timed
    public ResponseEntity getPage(PageModel pm) {
        service.findPage(pm);
        JSON rs = JsonUtil.getInstance().setRecurrenceStr("genTable_name").toJsonObject(pm);
        return ResultBuilder.buildObject(rs);
    }

    @GetMapping(value = "/edit")
    @Timed
    public String form(GenSchemeVo genSchemeVo, Boolean isModal, Model model) {
        if (StringUtil.isBlank(genSchemeVo.getPackageName())) {
            genSchemeVo.setPackageName("com.jqmkj.java.modules");
        }
        if (StringUtil.isBlank(genSchemeVo.getFunctionAuthor())) {
            genSchemeVo.setFunctionAuthor(SecurityUtil.getCurrentUser().getLoginId());
        }
        //同步模块数据
        genSchemeVo.setSyncModule(true);
        model.addAttribute("genSchemeVo", genSchemeVo);
        GenConfig config = GenUtil.getConfig();
        model.addAttribute("config", config);

        model.addAttribute("categoryList", FormDirective.convertComboDataList(config.getCategoryList(), Dict.F_VAL, Dict.F_NAME));
        model.addAttribute("viewTypeList", FormDirective.convertComboDataList(config.getViewTypeList(), Dict.F_VAL, Dict.F_NAME));

        List<GenTable> tableList = genTableService.findAll(), list = Lists.newArrayList();
        List<GenScheme> schemeList = service.findAll(genSchemeVo.getId());
        @SuppressWarnings("unchecked")
        List<String> tableIds = Collections3.extractToList(schemeList, "genTableId");
        for (GenTable table : tableList) {
            if (!tableIds.contains(table.getId())) {
                list.add(table);
            }
        }
        model.addAttribute("tableList", FormDirective.convertComboDataList(list, GenTable.F_ID, GenTable.F_NAMESANDTITLE));
        return PublicUtil.toAppendStr("modules/gen/genSchemeForm", isModal ? "Modal" : "");
    }

    @PostMapping(value = "/edit", produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity save(@Valid @RequestBody GenSchemeVo genSchemeVo) {
        service.save(genSchemeVo);
        SecurityUtil.clearUserJedisCache();
        if (genSchemeVo.getSyncModule()!=null && genSchemeVo.getSyncModule()) {
            GenTableVo genTableVo = genSchemeVo.getGenTable();
            if (genTableVo == null || PublicUtil.isEmpty(genTableVo.getClassName())) {
                genTableVo = genTableService.findOneVo(genSchemeVo.getGenTableId());
            }
            String url = PublicUtil.toAppendStr("/", StringUtil.lowerCase(genSchemeVo.getModuleName()), (StringUtil.isNotBlank(genSchemeVo.getSubModuleName()) ? "/" + StringUtil.lowerCase(genSchemeVo.getSubModuleName()) : ""), "/",
                    StringUtil.uncapitalize(genTableVo.getClassName()), "/");
            moduleService.generatorModuleData(genSchemeVo.getName(), genSchemeVo.getParentModuleId(), url);
            SecurityUtil.clearUserJedisCache();
        }
        // 生成代码
        if (genSchemeVo.getGenCode()!=null && genSchemeVo.getGenCode()) {
            service.generateCode(genSchemeVo);
        }
        return ResultBuilder.buildOk("保存", genSchemeVo.getName(), "成功");
    }

    @PostMapping(value = "/lock/{ids:" + Globals.LOGIN_REGEX
            + "}")
    @Timed
    public ResponseEntity lockOrUnLock(@PathVariable String ids) {
        log.debug("REST request to lockOrUnLock genTable: {}", ids);
        service.lockOrUnLock(Lists.newArrayList(ids.split(StringUtil.SPLIT_DEFAULT)));
        SecurityUtil.clearUserJedisCache();
        return ResultBuilder.buildOk("操作成功");
    }

    @PostMapping(value = "/delete/{ids:" + Globals.LOGIN_REGEX
            + "}")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity delete(@PathVariable String ids) {
        log.debug("REST request to delete User: {}", ids);
        service.deleteBatchIds(Lists.newArrayList(ids.split(StringUtil.SPLIT_DEFAULT)));
        return ResultBuilder.buildOk("删除成功");
    }


}
