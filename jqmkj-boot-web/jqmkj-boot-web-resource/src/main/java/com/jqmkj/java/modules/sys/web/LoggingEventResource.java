package com.jqmkj.java.modules.sys.web;

import com.jqmkj.java.modules.sys.domain.LoggingEvent;
import com.jqmkj.java.modules.sys.service.LoggingEventService;
import com.jqmkj.java.util.JsonUtil;
import com.jqmkj.java.util.domain.PageModel;
import com.jqmkj.java.web.rest.ResultBuilder;
import com.jqmkj.java.web.rest.base.BaseResource;
import com.alibaba.fastjson.JSON;
import com.codahale.metrics.annotation.Timed;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * 操作日志Controller 操作日志
 *
 * @author admin
 * @version 2017-01-03
 */
@Controller
@RequestMapping(value = "${jqmkj.adminPath}/sys/loggingEvent")
public class LoggingEventResource extends BaseResource {

    @Resource
    private LoggingEventService loggingEventService;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public String list() {
        return "modules/sys/loggingEventList";
    }

    /**
     * @param pm
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity getPage(PageModel<LoggingEvent> pm) {
        loggingEventService.findPage(pm);
        JSON rs = JsonUtil.getInstance().setRecurrenceStr().toJsonObject(pm);
        return ResultBuilder.buildObject(rs);
    }

}