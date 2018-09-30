package com.jqmkj.java.modules.sys.repository;

import com.jqmkj.java.common.persistence.repository.TreeRepository;
import com.jqmkj.java.modules.sys.domain.Org;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface OrgRepository extends TreeRepository<Org, String> {

    List<Org> findFirstByParentIdsLike(String parentIds);

    Optional<Org> findOneByName(String name);
}
