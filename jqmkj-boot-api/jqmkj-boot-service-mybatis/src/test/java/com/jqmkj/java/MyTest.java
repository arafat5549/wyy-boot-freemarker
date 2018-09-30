package com.jqmkj.java;

import com.jqmkj.java.common.persistence.domain.TreeEntity;
import com.jqmkj.java.config.TestConfig;
import com.jqmkj.java.modules.sys.domain.Module;
import com.jqmkj.java.modules.sys.repository.ModuleRepository;
import com.jqmkj.java.modules.sys.service.ModuleService;
import com.jqmkj.java.util.spring.SpringContextHolder;
import com.baomidou.mybatisplus.mapper.Condition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest//(classes = TestConfig.class)
@SpringBootConfiguration
//@Transactional
public class MyTest {

    @Autowired
    ModuleRepository moduleRepository;
    //@Autowired
    //ModuleService moduleService;

    //public static ModuleService moduleService = SpringContextHolder.getBean(ModuleService.class);

    @Test
    public void myTest(){
        //SecurityUtil.getModuleList();
        List<Module> list = moduleRepository.findRelationList(
            Condition.create().eq((TreeEntity.F_SQL_STATUS), 0)
                .orderBy((TreeEntity.F_SQL_SORT), true));
        System.out.println(list);
    }
}
