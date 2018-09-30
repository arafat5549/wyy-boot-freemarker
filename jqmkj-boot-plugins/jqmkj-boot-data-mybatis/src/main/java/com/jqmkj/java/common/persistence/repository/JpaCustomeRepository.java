package com.jqmkj.java.common.persistence.repository;

import com.jqmkj.java.common.persistence.domain.GeneralEntity;
import com.jqmkj.java.util.domain.ComboSearch;
import com.jqmkj.java.util.domain.ComboData;

import java.util.List;

public interface JpaCustomeRepository<T extends GeneralEntity> {


    List<ComboData> findJson(ComboSearch item);

}
