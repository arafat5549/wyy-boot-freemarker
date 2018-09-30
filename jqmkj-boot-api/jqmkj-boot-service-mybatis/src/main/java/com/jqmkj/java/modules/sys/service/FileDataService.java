/**
 * Copyright &copy; 2018 <a href="https://github.com/somewhereMrli/jqmkj-boot">jqmkj-boot</a> All rights reserved.
 */
package com.jqmkj.java.modules.sys.service;

import com.jqmkj.java.common.persistence.service.DataVoService;
import com.jqmkj.java.modules.sys.domain.FileData;
import com.jqmkj.java.modules.sys.repository.FileDataRepository;
import com.jqmkj.java.vo.sys.FileDataVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 文件管理Service 文件
 * @author admin
 * @version 2018-08-06
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FileDataService extends DataVoService<FileDataRepository, FileData, String, FileDataVo>{

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public void checkProperty(FileDataVo fileDataVo){
    }

}
