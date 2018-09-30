package com.jqmkj.java.modules.sys.service;

import com.jqmkj.java.common.persistence.DynamicSpecifications;
import com.jqmkj.java.common.persistence.SpecificationDetail;
import com.jqmkj.java.common.persistence.service.DataVoService;
import com.jqmkj.java.modules.sys.domain.Role;
import com.jqmkj.java.modules.sys.repository.RoleRepository;
import com.jqmkj.java.util.domain.PageModel;
import com.jqmkj.java.util.domain.QueryCondition;
import com.jqmkj.java.vo.sys.RoleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * Service class for managing roles.
 */
@Service
@Transactional
public class RoleService extends DataVoService<RoleRepository, Role, String, RoleVo> {


    @Override
    public RoleVo copyBeanToVo(Role role) {
        RoleVo roleResult = new RoleVo();
        BeanUtils.copyProperties(role, roleResult);
        if (role.getOrg() != null) {
            roleResult.setOrgName(role.getOrg().getName());
        }
        return roleResult;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Page<Role> findAll(PageModel<Role> pm, List<QueryCondition> queryConditions) {
        SpecificationDetail<Role> spec = DynamicSpecifications.buildSpecification(pm.getQueryConditionJson(), queryConditions,
                QueryCondition.ne(Role.F_STATUS, Role.FLAG_DELETE));
        return repository.findAll(spec, pm);
    }

    public List<Role> findAllList(boolean admin, Collection<QueryCondition> authQueryList) {

        SpecificationDetail<Role> spd = new SpecificationDetail<Role>()
                .and(QueryCondition.eq(Role.F_STATUS, Role.FLAG_NORMAL));
        if (admin) {
            spd.orAll(authQueryList);
        }
        spd.orderASC(Role.F_SORT);
        return repository.findAll(spd);
    }
}