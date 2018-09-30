/**
 * Copyright &copy; 2015 <a href="http://www.bs-innotech.com/">bs-innotech</a> All rights reserved.
 */
package com.jqmkj.java.modules.sys.service;

import com.jqmkj.java.common.persistence.DynamicSpecifications;
import com.jqmkj.java.common.persistence.SpecificationDetail;
import com.jqmkj.java.common.persistence.service.BaseService;
import com.jqmkj.java.modules.sys.domain.PersistentToken;
import com.jqmkj.java.modules.sys.domain.User;
import com.jqmkj.java.modules.sys.repository.PersistentTokenRepository;
import com.jqmkj.java.util.domain.PageModel;
import com.jqmkj.java.util.domain.QueryCondition;
import com.jqmkj.java.util.exception.RuntimeMsgException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * sessions Service
 *
 * @author admin
 * @version 2017-01-03
 */
@Service
@Transactional
public class PersistentTokenService extends BaseService<PersistentTokenRepository, PersistentToken, String> {
    public final Logger log = LoggerFactory.getLogger(getClass());

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public PersistentToken findOne(String id) {
        return repository.selectById(id);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public PageModel<PersistentToken>  findPage(PageModel<PersistentToken> pm) {
        //拼接查询动态对象
        SpecificationDetail<PersistentToken> spec = DynamicSpecifications.
            buildSpecification(pm.getQueryConditionJson());
        //动态生成sql分页查询
        findRelationPage(pm, spec);
        return pm;
    }

    public void delete(List<String> ids) {
        repository.deleteBatchIds(ids);
    }
}
