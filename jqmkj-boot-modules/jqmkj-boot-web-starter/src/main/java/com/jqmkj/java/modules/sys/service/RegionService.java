/**
 * Copyright &copy; 2018 <a href="https://github.com/somewhereMrli/jqmkj-boot">jqmkj-boot</a> All rights reserved.
 */
package com.jqmkj.java.modules.sys.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.jqmkj.java.util.base.Assert;
import com.jqmkj.java.common.persistence.service.DataVoService;
import com.jqmkj.java.modules.sys.domain.Region;
import com.jqmkj.java.modules.sys.domain.vo.RegionVo;
import com.jqmkj.java.modules.sys.repository.RegionRepository;

/**
 * 行政区域管理Service 行政区域
 * @author admin
 * @version 2018-09-30
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RegionService extends DataVoService<RegionRepository, Region, String, RegionVo>{

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public void checkProperty(RegionVo regionVo){
    }

}