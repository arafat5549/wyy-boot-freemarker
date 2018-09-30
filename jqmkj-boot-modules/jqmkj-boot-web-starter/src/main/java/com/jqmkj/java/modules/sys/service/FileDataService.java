/**
 * Copyright &copy; 2018 <a href="https://github.com/somewhereMrli/jqmkj-boot">jqmkj-boot</a> All rights reserved.
 */
package com.jqmkj.java.modules.sys.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.jqmkj.java.util.base.Assert;
import com.jqmkj.java.common.persistence.service.DataVoService;
import com.jqmkj.java.modules.sys.domain.FileData;
import com.jqmkj.java.modules.sys.domain.vo.FileDataVo;
import com.jqmkj.java.modules.sys.repository.FileDataRepository;

/**
 * 文件管理Service 文件
 * @author admin
 * @version 2018-09-30
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FileDataService extends DataVoService<FileDataRepository, FileData, String, FileDataVo>{

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public void checkProperty(FileDataVo fileDataVo){
    }

}