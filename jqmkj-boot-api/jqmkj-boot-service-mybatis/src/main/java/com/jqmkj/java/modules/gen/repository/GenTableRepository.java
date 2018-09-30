package com.jqmkj.java.modules.gen.repository;

import com.jqmkj.java.common.persistence.repository.BaseRepository;
import com.jqmkj.java.modules.gen.domain.GenTable;
import com.jqmkj.java.modules.gen.domain.vo.GenTableQuery;
import com.jqmkj.java.vo.gen.GenTableColumnVo;
import com.jqmkj.java.vo.gen.GenTableVo;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface GenTableRepository extends BaseRepository<GenTable, String> {
    /**
     * 查询表列表
     *
     * @param genTableQuery
     * @return
     */
    List<GenTable> findTableList(@Param("genTableQuery") GenTableQuery genTableQuery);

    /**
     * 获取数据表字段
     *
     * @param genTableVo
     * @return
     */
    List<GenTableColumnVo> findTableColumnList(@Param("genTableVo") GenTableVo genTableVo);

    /**
     * 获取数据表主键
     *
     * @param genTableVo
     * @return
     */
    List<String> findTablePK(@Param("genTableVo") GenTableVo genTableVo);

    List<GenTable> findAllByParentTable(String id);
}
