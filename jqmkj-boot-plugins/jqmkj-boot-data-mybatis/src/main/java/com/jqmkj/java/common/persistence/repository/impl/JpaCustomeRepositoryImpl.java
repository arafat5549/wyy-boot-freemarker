package com.jqmkj.java.common.persistence.repository.impl;

import com.jqmkj.java.common.persistence.domain.GeneralEntity;
import com.jqmkj.java.common.persistence.repository.JpaCustomeRepository;
import com.jqmkj.java.util.PublicUtil;
import com.jqmkj.java.util.domain.ComboData;
import com.jqmkj.java.util.domain.ComboQuery;
import com.jqmkj.java.util.domain.ComboSearch;
import com.baomidou.mybatisplus.MybatisSqlSessionTemplate;
import com.google.common.collect.Lists;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class JpaCustomeRepositoryImpl<T extends GeneralEntity>
        extends MybatisSqlSessionTemplate implements JpaCustomeRepository<T> {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected JpaCustomeRepositoryImpl(SqlSessionFactory sqlSessionFactory) {
        super(sqlSessionFactory);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<ComboData> findJson(ComboSearch comboSearch) {

        List<ComboData> mapList = Lists.newArrayList();
        if (PublicUtil.isNotEmpty(comboSearch) && PublicUtil.isNotEmpty(comboSearch.getId())
                && PublicUtil.isNotEmpty(comboSearch.getName()) && PublicUtil.isNotEmpty(comboSearch.getModule())) {
            StringBuffer sb = new StringBuffer()
                    .append(comboSearch.getId()).append(" as id,").append(comboSearch.getName()).append(" as name");
            boolean flag = PublicUtil.isNotEmpty(comboSearch.getParentId());
            if (flag) {
                sb.append(",").append(comboSearch.getParentId()).append(" as pId");
            }
            ComboQuery comboQuery = new ComboQuery();
            comboQuery.setColumns(sb.toString());
            comboQuery.setTableName(comboSearch.getModule());
            if (PublicUtil.isNotEmpty(comboSearch.getWhere())) {
                comboQuery.setCondition(comboSearch.getWhere());
            }
            if (PublicUtil.isNotEmpty(comboSearch.getSort())) {
                comboQuery.setSort(comboSearch.getSort());
            }
            mapList = selectList("_findByCombo", comboQuery);
        }
        return mapList;
    }


//    @Override
//    protected String getNamespace() {
//        return JpaCustomeRepositoryImpl.class.getName();
//    }
}
