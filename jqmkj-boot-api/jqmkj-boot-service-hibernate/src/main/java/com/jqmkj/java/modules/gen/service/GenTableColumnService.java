package com.jqmkj.java.modules.gen.service;

import com.jqmkj.java.common.persistence.domain.BaseEntity;
import com.jqmkj.java.common.persistence.service.DataVoService;
import com.jqmkj.java.modules.gen.domain.GenTableColumn;
import com.jqmkj.java.modules.gen.repository.GenTableColumnRepository;
import com.jqmkj.java.util.PublicUtil;
import com.jqmkj.java.vo.gen.GenTableColumnVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing genTables.
 */
@Service
@Transactional
public class GenTableColumnService extends DataVoService<GenTableColumnRepository,
        GenTableColumn, String, GenTableColumnVo> {


    public void deleteByTableId(String id, String currentAuditor) {
        baseRepository.createQuery(
                PublicUtil.toAppendStr("update GenTableColumn set status='", BaseEntity.FLAG_DELETE,
                        "', lastModifiedBy=:p2, lastModifiedDate=:p3 where genTable.id = :p1"),
                id, currentAuditor, PublicUtil.getCurrentDate()).executeUpdate();
    }


}
