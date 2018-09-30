package com.jqmkj.java.service;

import com.jqmkj.java.common.security.SecurityUtil;
import com.jqmkj.java.modules.sys.domain.Module;
import com.jqmkj.java.modules.sys.repository.FileDataRepository;
import com.jqmkj.java.modules.sys.repository.ModuleRepository;
import com.jqmkj.java.modules.sys.service.FileDataService;
import com.jqmkj.java.modules.sys.service.ModuleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.jqmkj.java.JqmkjBootWebApp.class)
public class MyTest {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private ModuleService moduleService;

    @Test
    public void moduleTest(){
        List<Module> list = moduleService.findAllByStatusOrderBySort(0);
        //System.out.println(list);
        list = SecurityUtil.getModuleList();
        for(Module m:list){
            System.out.println(m);
        }

    }
}
