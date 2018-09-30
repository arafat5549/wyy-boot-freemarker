package com.jqmkj.java.modules.sys.repository;

import com.jqmkj.java.common.persistence.repository.TreeRepository;
import com.jqmkj.java.modules.sys.domain.Module;
import com.jqmkj.java.modules.sys.domain.Org;

import java.util.List;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface OrgRepository extends TreeRepository<Org, String> {


    List<Org> selectListByRoleId(String roleId);
}
