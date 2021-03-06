package com.jqmkj.java.modules.sys.service;

import com.jqmkj.java.common.persistence.DynamicSpecifications;
import com.jqmkj.java.common.persistence.domain.BaseEntity;
import com.jqmkj.java.common.persistence.service.TreeVoService;
import com.jqmkj.java.modules.sys.domain.Dict;
import com.jqmkj.java.modules.sys.repository.DictRepository;
import com.jqmkj.java.util.PublicUtil;
import com.jqmkj.java.util.domain.QueryCondition;
import com.jqmkj.java.vo.base.SelectResult;
import com.jqmkj.java.vo.sys.DictVo;
import com.jqmkj.java.vo.sys.query.DictTreeQuery;
import com.jqmkj.java.vo.sys.query.DictTreeResult;
import com.baomidou.mybatisplus.mapper.Condition;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class for managing dicts.
 */
@Service
public class DictService extends TreeVoService<DictRepository, Dict, String, DictVo> {

    public List<Dict> findAllByStatusNotAndIsShowOrderBySortAsc(Integer status, Integer yes) {
        return repository.findRelationList(
            Condition.create()
                .ne(getClassNameProfix()+Dict.F_SQL_STATUS, status)
                .eq(getClassNameProfix()+Dict.F_SQL_ISSHOW, yes)
                .orderBy(getClassNameProfix()+Dict.F_SQL_SORT, true)
        );

    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<DictTreeResult> findTreeData(DictTreeQuery dictTreeQuery, List<Dict> dictList) {
        String type = dictTreeQuery != null ? dictTreeQuery.getType() : null, all = dictTreeQuery != null ? dictTreeQuery.getAll() : null;
        List<DictTreeResult> mapList = Lists.newArrayList();
        for (Dict e : dictList) {
            if ((all != null || (all == null && BaseEntity.FLAG_NORMAL.equals(e.getStatus())))) {
                DictTreeResult dictTreeResult = new DictTreeResult();
                dictTreeResult.setId(e.getId());
                dictTreeResult.setPid(PublicUtil.isEmpty(e.getParentId()) ? "0" : e.getParentId());
                dictTreeResult.setName(e.getName());
                dictTreeResult.setValue(e.getId());
                dictTreeResult.setLabel(dictTreeResult.getName());
                mapList.add(dictTreeResult);
            }
        }
        return mapList;
    }

//    @Transactional(readOnly = true, rollbackFor = Exception.class)
//    public List<Map<String, Object>> findTreeData(DictTreeQuery dictTreeQuery, List<Dict> dictList) {
//        String type = dictTreeQuery != null ? dictTreeQuery.getType() : null, all = dictTreeQuery != null ? dictTreeQuery.getAll() : null;
//        List<Map<String, Object>> mapList = Lists.newArrayList();
//        for (Dict e : dictList) {
//            if ((all != null || (all == null && BaseEntity.FLAG_NORMAL.equals(e.getStatus())))) {
//                Map<String, Object> map = Maps.newHashMap();
//                map.put("id", e.getId());
//                map.put("pId", PublicUtil.isEmpty(e.getParentId()) ? "0" : e.getParentId());
//                map.put("name", e.getName());
//                mapList.add(map);
//            }
//        }
//
//
//        return mapList;
//    }


    public SelectResult copyBeanToSelect(Dict item) {
        SelectResult selectResult = new SelectResult(item.getVal(), item.getName());
        return selectResult;
    }


}
