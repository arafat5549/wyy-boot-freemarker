package com.jqmkj.java.modules.sys.repository;

import com.jqmkj.java.common.persistence.repository.TreeRepository;
import com.jqmkj.java.modules.sys.domain.Dict;

import java.util.List;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface DictRepository extends TreeRepository<Dict, String> {



}
