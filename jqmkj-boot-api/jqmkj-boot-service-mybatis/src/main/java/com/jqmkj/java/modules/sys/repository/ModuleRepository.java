package com.jqmkj.java.modules.sys.repository;

import com.jqmkj.java.common.persistence.repository.TreeRepository;
import com.jqmkj.java.modules.sys.domain.Module;
import com.jqmkj.java.modules.sys.domain.Role;
import com.jqmkj.java.modules.sys.domain.User;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface ModuleRepository extends TreeRepository<Module, String> {

    List<Module> findAllAuthByUser(String userId);


    List<Module> selectListByRoleId(String roleId);

}
