//package com.jqmkj.java.modules.gen.web;
//
//import com.jqmkj.java.common.security.AuthoritiesConstants;
//import com.jqmkj.java.common.security.SecurityUtil;
//import com.jqmkj.java.modules.gen.domain.GenTemplate;
//import com.jqmkj.java.modules.gen.service.GenTemplateService;
//import com.jqmkj.java.util.JsonUtil;
//import com.jqmkj.java.util.StringUtil;
//import com.jqmkj.java.util.base.Reflections;
//import com.jqmkj.java.util.domain.Globals;
//import com.jqmkj.java.util.domain.PageModel;
//import com.jqmkj.java.util.exception.RuntimeMsgException;
//import com.jqmkj.java.web.rest.ResultBuilder;
//import com.jqmkj.java.web.rest.base.DataResource;
//import com.alibaba.fastjson.JSON;
//import com.codahale.metrics.annotation.Timed;
//import com.google.common.collect.Lists;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.annotation.Secured;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.net.URISyntaxException;
//
///**
// * 代码模板Controller
// *
// * @author wyy
// */
//@Controller
//@RequestMapping(value = "${jqmkj.adminPath}/gen/genTemplate")
//public class GenTemplateResource extends DataResource<GenTemplateService, GenTemplate> {
//
//    @GetMapping(value = "/")
//    @Timed
//    public String list() {
//        return "modules/sys/genTemplateList";
//    }
//
//    /**
//     * @param pm
//     * @return
//     */
//    @GetMapping(value = "/page")
//    public ResponseEntity getPage(PageModel<GenTemplate> pm) {
//
//        service.findPage(pm);
//        JSON rs = JsonUtil.getInstance().setRecurrenceStr("org_name").toJsonObject(pm);
//        return ResultBuilder.buildObject(rs);
//    }
//
//    @GetMapping(value = "/edit")
//    @Timed
//    public String form(GenTemplate genTemplate) {
//        return "modules/sys/TemplateForm";
//    }
//
//    /**
//     * @param genTemplate
//     * @return
//     * @throws URISyntaxException
//     */
//    @PostMapping(value = "/edit", produces = MediaType.APPLICATION_JSON_VALUE)
//    @Timed
//    public ResponseEntity save(GenTemplate genTemplate)
//            throws URISyntaxException {
//        log.debug("REST request to save GenTemplateVo : {}", genTemplate);
//        // Lowercase the genTable login before comparing with database
//        if (!checkByProperty(Reflections.createObj(GenTemplate.class, Lists.newArrayList(GenTemplate.F_ID, GenTemplate.F_NAME), genTemplate.getId(),
//                genTemplate.getName()))) {
//            throw new RuntimeMsgException("名称已存在");
//        }
//        service.save(genTemplate);
//        SecurityUtil.clearUserJedisCache();
//        return ResultBuilder.buildOk("保存", genTemplate.getName(), "成功");
//    }
//
//    /**
//     * @param ids
//     * @return
//     */
//    @PostMapping(value = "/delete/{ids:" + Globals.LOGIN_REGEX
//            + "}")
//    @Timed
//    public ResponseEntity delete(@PathVariable String ids) {
//        log.debug("REST request to delete GenTemplateVo: {}", ids);
//        service.delete(Lists.newArrayList(ids.split(StringUtil.SPLIT_DEFAULT)));
//        SecurityUtil.clearUserJedisCache();
//        return ResultBuilder.buildOk("删除成功");
//    }
//
//    @PostMapping(value = "/lock/{ids:" + Globals.LOGIN_REGEX
//            + "}")
//    @Timed
//    @Secured(AuthoritiesConstants.ADMIN)
//    public ResponseEntity lockOrUnLock(@PathVariable String ids) {
//        log.debug("REST request to lockOrUnLock GenTemplateVo: {}", ids);
//        service.lockOrUnLock(Lists.newArrayList(ids.split(StringUtil.SPLIT_DEFAULT)));
//        SecurityUtil.clearUserJedisCache();
//        return ResultBuilder.buildOk("操作成功");
//    }
//
//}
