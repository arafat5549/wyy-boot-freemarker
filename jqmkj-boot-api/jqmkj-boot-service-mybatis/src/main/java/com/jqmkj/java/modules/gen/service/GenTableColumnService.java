package com.jqmkj.java.modules.gen.service;

import com.jqmkj.java.common.persistence.service.DataVoService;
import com.jqmkj.java.modules.gen.domain.GenTableColumn;
import com.jqmkj.java.modules.gen.repository.GenTableColumnRepository;
import com.jqmkj.java.util.base.Assert;
import com.jqmkj.java.vo.gen.GenTableColumnVo;
import com.baomidou.mybatisplus.mapper.Condition;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing genTables.
 *
 * @author wyy
 */
@Service
public class GenTableColumnService extends
    DataVoService<GenTableColumnRepository, GenTableColumn, String, GenTableColumnVo> {

    List<GenTableColumn> findAllByGenTableIdOrderBySort(String id){
        return selectList(Condition.create().eq(GenTableColumn.F_SQL_GENTABLEID,id)
            .orderBy(GenTableColumn.F_SQL_SORT));
    }


    public void deleteByTableId(String id, String currentAuditor) {
        List<GenTableColumn> genTableColumnList = findAllByGenTableIdOrderBySort(id);
        Assert.assertNotNull(genTableColumnList, "id " + id + " genTableColumn 不能为空");
        deleteById(genTableColumnList.stream().map(item->item.getId()).collect(Collectors.toList()));

    }


}
