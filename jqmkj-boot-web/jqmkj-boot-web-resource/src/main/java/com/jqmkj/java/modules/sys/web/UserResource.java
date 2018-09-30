package com.jqmkj.java.modules.sys.web;

import com.jqmkj.java.common.config.template.tag.FormDirective;
import com.jqmkj.java.common.security.SecurityUtil;
import com.jqmkj.java.common.security.SecurityUtil;
import com.jqmkj.java.modules.sys.domain.Role;
import com.jqmkj.java.modules.sys.service.ModuleService;
import com.jqmkj.java.modules.sys.service.UserService;
import com.jqmkj.java.util.JsonUtil;
import com.jqmkj.java.util.PublicUtil;
import com.jqmkj.java.util.StringUtil;
import com.jqmkj.java.util.base.Reflections;
import com.jqmkj.java.util.domain.Globals;
import com.jqmkj.java.util.domain.PageModel;
import com.jqmkj.java.util.excel.ExportExcel;
import com.jqmkj.java.util.excel.ImportExcel;
import com.jqmkj.java.util.exception.RuntimeMsgException;
import com.jqmkj.java.vo.sys.UserExcelVo;
import com.jqmkj.java.vo.sys.UserVo;
import com.jqmkj.java.web.rest.ResultBuilder;
import com.jqmkj.java.web.rest.base.DataVoResource;
import com.alibaba.fastjson.JSON;
import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for managing user.
 * <p>
 * <p>
 * This class accesses the User entity, and needs to fetch its collection of
 * authorities.
 * </p>
 * <p>
 * For a normal use-case, it would be better to have an eager relationship
 * between User and Authority, and send everything to the client side: there
 * would be no View Model and DTO, a lot less code, and an outer-join which
 * would be good for performance.
 * </p>
 * <p>
 * We use a View Model and a DTO for 3 reasons:
 * <ul>
 * <li>We want to keep a lazy association between the user and the authorities,
 * because people will quite often do relationships with the user, and we don't
 * want them to get the authorities all the time for nothing (for performance
 * reasons). This is the #1 goal: we should not impact our user' application
 * because of this use-case.</li>
 * <li>Not having an outer join causes n+1 requests to the database. This is not
 * a real issue as we have by default a second-level cache. This means on the
 * first HTTP call we do the n+1 requests, but then all authorities come from
 * the cache, so in fact it's much better than doing an outer join (which will
 * get lots of data from the database, for each HTTP call).</li>
 * <li>As this manages user, for security reasons, we'd rather have a DTO
 * layer.</li>
 * </ul>
 * <p>
 * Another option would be to have a specific JPA entity graph to handle this
 * case.
 * </p>
 *
 * @author wyy
 */
@Controller
@RequestMapping("${jqmkj.adminPath}/sys/user")
public class UserResource extends DataVoResource<UserService, UserVo> {


    @Autowired(required = false)
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModuleService moduleService;

    public UserResource(UserService service) {
        super(service);
    }


    @GetMapping(value = "/")
    @Timed
    public String list() {
        return "modules/sys/userList";
    }

    /**
     * 分页
     *
     * @param pm
     */
    @GetMapping(value = "/page")
    public ResponseEntity getPage(PageModel pm) {
        pm = service.findTablePage(pm, SecurityUtil.dataScopeFilterSql("b", "a"));
        JSON rs = JsonUtil.getInstance()
            .toJsonObject(pm);
        return ResultBuilder.buildObject(rs);
    }


    /**
     * GET  /users/:id : get the "login" user.
     *
     * @return the ResponseEntity with status 200 (OK) and with body the "id" user, or with status 404 (Not Found)
     */
    @GetMapping("/authorities")
    @Timed
    public ResponseEntity authorities() {
        String id = SecurityUtil.getCurrentUserId();
        log.debug("REST request to authorities  : {}", id);
        return ResultBuilder.buildOk(SecurityUtil.getModuleList().stream().map(item -> moduleService.copyBeanToVo(item)).collect(Collectors.toList()));
    }



    @GetMapping(value = "/edit", produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
//	@Secured(AuthoritiesConstants.ADMIN)
    public String form(UserVo userVo, Model model, @RequestParam(required = false) Boolean isModal) {
        model.addAttribute("allRoles", FormDirective.convertComboDataList(SecurityUtil.getRoleList(), Role.F_ID, Role.F_NAME));
        return PublicUtil.toAppendStr("modules/sys/userForm", isModal ? "Modal" : "");
    }

    /**
     * 保存
     *
     * @param userVo
     * @return
     */
    @PostMapping(value = "/edit", produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @ApiImplicitParams(@ApiImplicitParam(paramType = "query", name = "confirmPassword"))
    public ResponseEntity save(@Valid @RequestBody UserVo userVo) {
        log.debug("REST request to save userVo : {}", userVo);
        // beanValidatorAjax(user);
        if (PublicUtil.isNotEmpty(userVo.getPassword()) && !userVo.getPassword().equals(userVo.getConfirmPassword())) {
            throw new RuntimeMsgException("两次输入密码不一致");
        }
        // Lowercase the user login before comparing with database
        service.checkProperty(userVo);
        if (PublicUtil.isNotEmpty(userVo.getId())) {
            UserVo temp = service.findOneVo(userVo.getId());
            userVo.setPassword(PublicUtil.isEmpty(userVo.getPassword()) ? temp.getPassword() : passwordEncoder.encode(userVo.getPassword()));
        } else {
            userVo.setPassword(passwordEncoder.encode(userVo.getPassword()));
        }
        service.save(userVo);
        SecurityUtil.clearUserJedisCache();
        SecurityUtil.clearUserLocalCache();
        return ResultBuilder.buildOk("保存", userVo.getLoginId(), "成功");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @PostMapping(value = "/delete/{ids:" + Globals.LOGIN_REGEX + "}")
    @Timed
    public ResponseEntity delete(@PathVariable String ids) {
        log.debug("REST request to delete User: {}", ids);
        service.deleteBatchIds(Lists.newArrayList(ids.split(StringUtil.SPLIT_DEFAULT)));
        SecurityUtil.clearUserJedisCache();
        SecurityUtil.clearUserLocalCache();
        return ResultBuilder.buildOk("删除成功");
    }

    /**
     * 锁定or解锁
     *
     * @param ids
     * @return
     */
    @PostMapping(value = "/lock/{ids:" + Globals.LOGIN_REGEX + "}")
    @Timed
    public ResponseEntity lockOrUnLock(@PathVariable String ids) {
        log.debug("REST request to lockOrUnLock User: {}", ids);
        service.lockOrUnLock(Lists.newArrayList(ids.split(StringUtil.SPLIT_DEFAULT)));
        SecurityUtil.clearUserJedisCache();
        SecurityUtil.clearUserLocalCache();
        return ResultBuilder.buildOk("操作成功");
    }


    @PostMapping(value = "/uploadData")
    @Timed
    public ResponseEntity uploadData(@RequestParam("uploadFile") MultipartFile dataFile, HttpServletResponse response) throws IOException, InvalidFormatException, IllegalAccessException, InstantiationException {
        if(dataFile.isEmpty()){
            return ResultBuilder.buildFailed("上传文件为空");
        }
        ImportExcel importExcel = new ImportExcel(dataFile, 1, 0);
        List<UserExcelVo> dataList = importExcel.getDataList(UserExcelVo.class);
        for(UserExcelVo userExcelVo : dataList){
            if(userExcelVo.getPhone().length()!=11){
                BigDecimal bd = new BigDecimal(userExcelVo.getPhone());
                userExcelVo.setPhone(bd.toPlainString());
            }
            if (!checkByProperty(Reflections.createObj(UserVo.class, Lists.newArrayList(UserVo.F_LOGINID),
                userExcelVo.getLoginId()))) {
                throw new RuntimeMsgException("登录Id"+userExcelVo.getLoginId()+"已存在");
            }
            if (PublicUtil.isNotEmpty(userExcelVo.getPhone()) && !checkByProperty(Reflections.createObj(UserVo.class,
                Lists.newArrayList(UserVo.F_PHONE), userExcelVo.getPhone()))) {
                throw new RuntimeMsgException("手机"+userExcelVo.getPhone()+"已存在");
            }
            if (PublicUtil.isNotEmpty(userExcelVo.getEmail()) && !checkByProperty(Reflections.createObj(UserVo.class,
                Lists.newArrayList(UserVo.F_EMAIL), userExcelVo.getEmail()))) {
                throw new RuntimeMsgException("邮箱"+userExcelVo.getEmail()+"已存在");
            }
            //初始密码
            userExcelVo.setPassword(passwordEncoder.encode("123456"));
            service.save(userExcelVo);
        }
        return ResultBuilder.buildOk("操作成功");

    }
    @GetMapping(value = "/importTemplate")
    @Timed
    public void importTemplate(HttpServletResponse response) throws IOException {

        response.setContentType("multipart/form-data");
        String fileName = /*creatUUID()*/"用户信息导入模板" + ".xlsx";
        // 2.设置文件头：最后一个参数是设置下载文件名
        response.setHeader("Content-Disposition",
            "attachment;fileName=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
        response.setCharacterEncoding("utf-8");

        ExportExcel exportExcel = new ExportExcel("用户信息维护", UserExcelVo.class);

        exportExcel.write(response.getOutputStream()).dispose();
    }

}
