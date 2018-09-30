/**
 * Copyright &copy; 2015 <a href="http://www.bs-innotech.com/">bs-innotech</a> All rights reserved.
 */
package com.jqmkj.java.modules.sys.service;

import com.jqmkj.java.common.persistence.DynamicSpecifications;
import com.jqmkj.java.common.persistence.SpecificationDetail;
import com.jqmkj.java.common.persistence.service.BaseService;
import com.jqmkj.java.modules.sys.domain.LoggingEvent;
import com.jqmkj.java.modules.sys.repository.LoggingEventRepository;
import com.jqmkj.java.util.domain.PageModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 操作日志Service 操作日志
 *
 * @author admin
 * @version 2017-01-03
 */
@Service
@Transactional
public class LoggingEventService extends BaseService<LoggingEventRepository, LoggingEvent, String> {

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public LoggingEvent findOne(String id) {
        return repository.selectById(id);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public PageModel<LoggingEvent> findPage(PageModel<LoggingEvent> pm) {
        SpecificationDetail<LoggingEvent> spec = DynamicSpecifications.buildSpecification(pm.getQueryConditionJson());
        return findPage(pm, spec);
    }
}
