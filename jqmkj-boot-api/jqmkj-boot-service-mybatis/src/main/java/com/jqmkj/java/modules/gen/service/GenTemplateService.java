package com.jqmkj.java.modules.gen.service;

import com.jqmkj.java.common.persistence.DynamicSpecifications;
import com.jqmkj.java.common.persistence.service.DataService;
import com.jqmkj.java.modules.gen.domain.GenTable;
import com.jqmkj.java.modules.gen.domain.GenTemplate;
import com.jqmkj.java.modules.gen.repository.GenTemplateRepository;
import com.jqmkj.java.util.domain.QueryCondition;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class for managing genTables.
 *
 * @author wyy
 */
@Service
public class GenTemplateService extends DataService<GenTemplateRepository, GenTemplate, String> {


    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<GenTemplate> findAll() {
        return findAll(DynamicSpecifications.bySearchQueryCondition(QueryCondition.ne(GenTable.F_STATUS, GenTable.FLAG_DELETE)));
    }

}
