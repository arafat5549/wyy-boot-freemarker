package com.jqmkj.java.modules.sys.web;

import com.jqmkj.java.common.security.SecurityUtil;
import com.jqmkj.java.modules.sys.domain.TaskScheduleJob;
import com.jqmkj.java.modules.sys.service.TaskScheduleJobExcutorService;
import com.jqmkj.java.util.JsonUtil;
import com.jqmkj.java.util.PublicUtil;
import com.jqmkj.java.util.StringUtil;
import com.jqmkj.java.util.domain.Globals;
import com.jqmkj.java.util.domain.PageModel;
import com.jqmkj.java.util.exception.RuntimeMsgException;
import com.jqmkj.java.vo.sys.TaskScheduleJobVo;
import com.jqmkj.java.web.rest.ResultBuilder;
import com.jqmkj.java.web.rest.base.DataVoResource;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 任务调度管理Controller 任务调度
 *
 * @author lj
 * @version 2017-01-23
 */
@ConditionalOnProperty(name = Globals.ALBEDO_QUARTZENABLED)
@Controller
@RequestMapping(value = "${jqmkj.adminPath}/sys/taskScheduleJob")
public class TaskScheduleJobResource extends DataVoResource<TaskScheduleJobExcutorService, TaskScheduleJobVo> {


    public TaskScheduleJobResource(TaskScheduleJobExcutorService service) {
        super(service);
    }

    @GetMapping(value = "/")
    public String list() {
        return "modules/sys/taskScheduleJobList";
    }

    /**
     * @param pm
     */
    @GetMapping(value = "/page")
    public ResponseEntity getPage(PageModel<TaskScheduleJob> pm) {
        pm = service.findAll(pm, SecurityUtil.dataScopeFilter());
        JSON rs = JsonUtil.getInstance().setRecurrenceStr().toJsonObject(pm);
        return ResultBuilder.buildObject(rs);
    }

    /**
     * @param taskScheduleJobVo
     * @return
     */
    @GetMapping(value = "/edit", produces = MediaType.APPLICATION_JSON_VALUE)
    public String form(TaskScheduleJobVo taskScheduleJobVo) {
        if (taskScheduleJobVo == null) {
            throw new RuntimeMsgException(PublicUtil.toAppendStr("无法查找到任务调度"));
        }
        return "modules/sys/taskScheduleJobForm";
    }

    /**
     * @param taskScheduleJobVo
     * @return
     */
    @PostMapping(value = "/edit", produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity save(@Valid @RequestBody TaskScheduleJobVo taskScheduleJobVo) {
        log.debug("REST request to save TaskScheduleJobVo : {}", taskScheduleJobVo);
        service.save(taskScheduleJobVo);

        return ResultBuilder.buildOk("保存任务调度成功");
    }

    /**
     * @param ids
     * @return
     */
    @PostMapping(value = "/delete/{ids:" + Globals.LOGIN_REGEX
            + "}")

    public ResponseEntity delete(@PathVariable String ids) {
        log.debug("REST request to delete TaskScheduleJob: {}", ids);
        service.deleteBatchIds(Lists.newArrayList(ids.split(StringUtil.SPLIT_DEFAULT)));
        return ResultBuilder.buildOk("删除任务调度成功");
    }

    /**
     * @param ids
     * @return
     */
    @PostMapping(value = "/lock/{ids:" + Globals.LOGIN_REGEX
            + "}")

    public ResponseEntity lockOrUnLock(@PathVariable String ids) {
        log.debug("REST request to lockOrUnLock TaskScheduleJob: {}", ids);
        service.lockOrUnLock(Lists.newArrayList(ids.split(StringUtil.SPLIT_DEFAULT)));
        return ResultBuilder.buildOk("操作任务调度成功");
    }

}
