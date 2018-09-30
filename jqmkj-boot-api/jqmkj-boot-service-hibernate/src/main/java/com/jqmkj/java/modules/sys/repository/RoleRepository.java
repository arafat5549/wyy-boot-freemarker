package com.jqmkj.java.modules.sys.repository;

import com.jqmkj.java.common.persistence.repository.DataRepository;
import com.jqmkj.java.modules.sys.domain.Role;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface RoleRepository extends DataRepository<Role, String> {

}
