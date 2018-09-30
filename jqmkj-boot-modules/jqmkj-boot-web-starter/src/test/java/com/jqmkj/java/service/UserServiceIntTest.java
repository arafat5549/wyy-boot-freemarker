//package com.jqmkj.java.service;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.jqmkj.java.JqmkjJhipsterWeb;
//import com.jqmkj.java.common.repository.data.JpaCustomeRepository;
//import com.jqmkj.CustomPersistentRememberMeServices;
//import com.jqmkj.java.modules.sys.service.UserService;
//import com.jqmkj.java.util.domain.PageModel;
//
///**
// * Test class for the UserResource REST controller.
// *
// * @see UserService
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = JqmkjJhipsterWeb.class)
//@Transactional
//public class UserServiceIntTest {
//
//    @Autowired
//	public JpaCustomeRepository baseRepository;
//
//    @Test
//    public void testRemoveOldPersistentTokens() {
//    	PageModel pm =new PageModel(1, 20);
//    	pm.setQueryConditionJson("[{\"fieldName\":\"a.event_id\",\"attrType\":\"String\",\"fieldNode\":\"\",\"operate\":\"like\",\"weight\":0,\"value\":\"234\"}]");
//    	baseRepository.findSQLPage("SELECT a.event_id as id, a.caller_class as callerClass from logging_event a", pm);
//    	System.out.println(pm.getData());
//    }
//
//}
