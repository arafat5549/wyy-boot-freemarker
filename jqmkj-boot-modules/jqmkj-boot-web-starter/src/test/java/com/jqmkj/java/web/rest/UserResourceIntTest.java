package com.jqmkj.java.web.rest;

import com.jqmkj.java.common.config.JqmkjProperties;
import com.jqmkj.java.common.persistence.DynamicSpecifications;
import com.jqmkj.java.common.persistence.SpecificationDetail;
import com.jqmkj.java.common.persistence.domain.DataEntity;
import com.jqmkj.java.common.security.MailService;
import com.jqmkj.java.modules.sys.domain.Org;
import com.jqmkj.java.modules.sys.domain.Role;
import com.jqmkj.java.modules.sys.domain.User;
import com.jqmkj.java.modules.sys.service.OrgService;
import com.jqmkj.java.modules.sys.service.RoleService;
import com.jqmkj.java.modules.sys.service.UserService;
import com.jqmkj.java.modules.sys.web.UserResource;
import com.jqmkj.java.util.PublicUtil;
import com.jqmkj.java.util.base.Collections3;
import com.jqmkj.java.util.domain.Globals;
import com.jqmkj.java.util.domain.QueryCondition;
import com.jqmkj.java.vo.sys.UserVo;
import com.jqmkj.java.JqmkjBootWebApp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the UserResource REST controller.
 *
 * @see UserResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JqmkjBootWebApp.class)
public class UserResourceIntTest {

    private static final Long DEFAULT_ID = 1L;

    private static final String DEFAULT_ANOTHER_LOGIN = "johndoeddd";
    private static final String DEFAULT_LOGIN = "johndoe";
    private static final String UPDATED_LOGIN = "jhipster";

    private static final String DEFAULT_PASSWORD = "passjohndoe";
    private static final String UPDATED_PASSWORD = "passjhipster";

    private static final String DEFAULT_PHONE = "13258812456";
    private static final String UPDATED_PHONE = "13222222222";

    private static final String DEFAULT_ANOTHER_EMAIL = "23423432@localhost";
    private static final String DEFAULT_EMAIL = "johndoe@localhost";
    private static final String UPDATED_EMAIL = "jhipster@localhost";


    private static final String DEFAULT_NAME = "doe";
    private static final String UPDATED_NAME = "jhipsterLastName";
    private static final String DEFAULT_TYPE = "system";
    private static final String UPDATED_TYPE = "ios";
    private static final String DEFAULT_KEY = "key1";
    private static final String UPDATED_KEY = "key2";

    private static final String DEFAULT_LANGKEY = "en";
    private static final String UPDATED_LANGKEY = "fr";

    @Autowired
    private MailService mailService;

    @Autowired
    private UserService userService;
    @Autowired
    private OrgService orgService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private JqmkjProperties jqmkjProperties;

    private MockMvc restUserMockMvc;

    private User user;
    List<Org> orgs;
    List<Role> roles;
    @Autowired
    protected WebApplicationContext webApplicationContext;
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.restUserMockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .build();
    }

    /**
     * Create a User.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which has a required relationship to the User entity.
     */
    public User createEntity() {
        User user = new User();
        user.setLoginId(DEFAULT_LOGIN);
        user.setPassword(PublicUtil.getRandomString(60));
        user.setEmail(DEFAULT_EMAIL);
        user.setPhone(DEFAULT_PHONE);
        user.setName(DEFAULT_NAME);
        user.setLangKey(DEFAULT_LANGKEY);
        user.setOrg(orgs.get(0));
        user.setRoleIdList(Collections3.extractToList(roles, Role.F_ID));
        return user;
    }

    @Before
    public void initTest() {
        orgs = orgService.findAll();
        roles = roleService.findAll();
        user = createEntity();
        // Initialize the database

        User anotherUser = new User();
        anotherUser.setLoginId(DEFAULT_ANOTHER_LOGIN);
        anotherUser.setPassword(PublicUtil.getRandomString(60));
        anotherUser.setEmail(DEFAULT_ANOTHER_EMAIL);
        anotherUser.setName("java");
        anotherUser.setLangKey("en");
        userService.save(anotherUser);
    }

    @Test
    @Transactional
    public void createUser() throws Exception {
        userService.delete(null);
        int databaseSizeBeforeCreate = userService.findAll().size();

        // Create the User
        UserVo managedUserVM = new UserVo(null,
            DEFAULT_LOGIN,
            DEFAULT_PASSWORD,
            DEFAULT_PASSWORD,
            orgs.get(0).getId(),
            DEFAULT_TYPE,
            DEFAULT_KEY,
            DEFAULT_NAME,
            DEFAULT_PHONE,
            DEFAULT_EMAIL,
            DEFAULT_LANGKEY,
            null,
            null,
            null,
            Collections3.extractToList(roles, Role.F_ID),
            null,
            null,
            null,null);

        restUserMockMvc.perform(post(jqmkjProperties.getAdminPath("/sys/user/edit"))
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(managedUserVM)))
            .andExpect(status().isOk());

        // Validate the User in the database
        List<User> userList = userService.findAll();
        assertThat(userList).hasSize(databaseSizeBeforeCreate + 1);
        User testUser = userService.findOneByLoginId(managedUserVM.getLoginId()).get();
        assertThat(testUser.getLoginId()).isEqualTo(DEFAULT_LOGIN);
        assertThat(testUser.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testUser.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testUser.getLangKey()).isEqualTo(DEFAULT_LANGKEY);
    }

    @Test
    @Transactional
    public void createUserWithExistingEmail() throws Exception {
        int databaseSizeBeforeCreate = userService.findAll().size();

        // Create the User
        UserVo managedUserVM = new UserVo(null,
            DEFAULT_ANOTHER_LOGIN,
            DEFAULT_PASSWORD,
            DEFAULT_PASSWORD,
            orgs.get(0).getId(),
            DEFAULT_TYPE,
            DEFAULT_KEY,
            DEFAULT_NAME,
            DEFAULT_PHONE,
            DEFAULT_EMAIL,
            DEFAULT_LANGKEY,
            null,
            null,
            null,
            Collections3.extractToList(roles, Role.F_ID),
            null,
            null,
            null,null);

        // Create the User
        restUserMockMvc.perform(post(jqmkjProperties.getAdminPath("/sys/user/edit"))
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(managedUserVM)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value(Globals.MSG_TYPE_WARNING))
            .andExpect(jsonPath("$.message").isNotEmpty());

        // Validate the User in the database
        List<User> userList = userService.findAll();
        assertThat(userList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllUsers() throws Exception {
        // Initialize the database
        userService.save(user);
        // Get all the users
        restUserMockMvc.perform(get(jqmkjProperties.getAdminPath("/sys/user/page?sortName=a.created_date desc"))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.data.[*].loginId").value(hasItem(DEFAULT_LOGIN)))
            .andExpect(jsonPath("$.data.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.data.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.data.[*].email").value(hasItem(DEFAULT_EMAIL)));
//            .andExpect(jsonPath("$.data.[*].langKey").value(hasItem(DEFAULT_LANGKEY)));
    }

    @Test
    @Transactional
    public void getUser() throws Exception {
        // Initialize the database
        userService.save(user);

        // Get the user
        restUserMockMvc.perform(get(jqmkjProperties.getAdminPath("/sys/user/{id}"), user.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.loginId").value(user.getLoginId()))
            .andExpect(jsonPath("$.data.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.data.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.data.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.data.langKey").value(DEFAULT_LANGKEY));
    }

    @Test
    @Transactional
    public void getNonExistingUser() throws Exception {
        restUserMockMvc.perform(get(jqmkjProperties.getAdminPath("/sys/user/ddd/unknown")))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUser() throws Exception {
        // Initialize the database
        userService.save(user);
        int databaseSizeBeforeUpdate = userService.findAll().size();

        // Update the user
        User updatedUser = userService.findOne(user.getId());

        UserVo managedUserVM = new UserVo(null,
            UPDATED_LOGIN,
            UPDATED_PASSWORD,
            UPDATED_PASSWORD,
            orgs.get(0).getId(),
            UPDATED_TYPE,
            UPDATED_KEY,
            UPDATED_NAME,
            UPDATED_PHONE,
            UPDATED_EMAIL,
            UPDATED_LANGKEY,
            null,
            null,
            null,
            Collections3.extractToList(roles, Role.F_ID),
            null,
            null,
            null,null);
        managedUserVM.setId(updatedUser.getId());
        restUserMockMvc.perform(post(jqmkjProperties.getAdminPath("/sys/user/edit"))
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(managedUserVM)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value(Globals.MSG_TYPE_SUCCESS));

        // Validate the User in the database
        List<User> userList = userService.findAll();
        assertThat(userList).hasSize(databaseSizeBeforeUpdate);
        User testUser = userService.findOne(updatedUser.getId());
        assertThat(testUser.getLoginId()).isEqualTo(UPDATED_LOGIN);
        assertThat(testUser.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testUser.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testUser.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testUser.getLangKey()).isEqualTo(UPDATED_LANGKEY);
    }


    @Test
    @Transactional
    public void updateUserExistingEmail() throws Exception {

        userService.save(user);
        // Update the user
        User updatedUser = userService.findOne(user.getId());


        UserVo managedUserVM = new UserVo(null,
            UPDATED_LOGIN,
            UPDATED_PASSWORD,
            UPDATED_PASSWORD,
            orgs.get(0).getId(),
            UPDATED_TYPE,
            UPDATED_KEY,
            UPDATED_NAME,
            UPDATED_PHONE,
            DEFAULT_ANOTHER_EMAIL,
            UPDATED_LANGKEY,
            null,
            null,
            null,
            Collections3.extractToList(roles, Role.F_ID),
            null,
            null,
            null,null);
        managedUserVM.setId(updatedUser.getId());
        restUserMockMvc.perform(post(jqmkjProperties.getAdminPath("/sys/user/edit"))
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(managedUserVM)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value(Globals.MSG_TYPE_WARNING))
            .andExpect(jsonPath("$.message").isNotEmpty());

    }

    @Test
    @Transactional
    public void updateUserExistingLogin() throws Exception {

        userService.save(user);
        // Update the user
        User updatedUser = userService.findOne(user.getId());

        UserVo managedUserVM = new UserVo(null,
            DEFAULT_ANOTHER_LOGIN,
            UPDATED_PASSWORD,
            UPDATED_PASSWORD,
            orgs.get(0).getId(),
            UPDATED_TYPE,
            UPDATED_KEY,
            UPDATED_NAME,
            UPDATED_PHONE,
            UPDATED_EMAIL,
            UPDATED_LANGKEY,
            null,
            null,
            null,
            Collections3.extractToList(roles, Role.F_ID),
            null,
            null,null,null
        );
        managedUserVM.setId(updatedUser.getId());
        restUserMockMvc.perform(post(jqmkjProperties.getAdminPath("/sys/user/edit"))
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(managedUserVM)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value(Globals.MSG_TYPE_WARNING))
            .andExpect(jsonPath("$.message").isNotEmpty());
    }
    @Test
    @Transactional
    public void lockOrUnLockUser() throws Exception {
        // Initialize the database
        userService.save(user);
        SpecificationDetail<User> specificationDetail =
            DynamicSpecifications.bySearchQueryCondition(QueryCondition.eq(DataEntity.F_STATUS, DataEntity.FLAG_NORMAL));
        long databaseSizeBeforeLock = userService.findAll(specificationDetail).size();

        // Delete the user
        restUserMockMvc.perform(put(jqmkjProperties.getAdminPath("/sys/user/{id}"), user.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        long databaseSizeAfterLock = userService.findAll(specificationDetail).size();
        assertThat(databaseSizeAfterLock == databaseSizeBeforeLock - 1);
    }
    @Test
    @Transactional
    public void deleteUser() throws Exception {
        // Initialize the database
        userService.save(user);
        SpecificationDetail<User> specificationDetail =
            DynamicSpecifications.bySearchQueryCondition(QueryCondition.eq(DataEntity.F_STATUS, DataEntity.FLAG_NORMAL));
        long databaseSizeBeforeDelete = userService.findAll(specificationDetail).size();

        // Delete the user
        restUserMockMvc.perform(delete(jqmkjProperties.getAdminPath("/sys/user/{id}"), user.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        long databaseSizeAfterDelete = userService.findAll(specificationDetail).size();
        assertThat(databaseSizeAfterDelete == databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void getAllAuthorities() throws Exception {
        restUserMockMvc.perform(get(jqmkjProperties.getAdminPath("/sys/user/authorities"))
            .accept(TestUtil.APPLICATION_JSON_UTF8)
            .contentType(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    @Transactional
    public void testUserEquals() throws Exception {
        TestUtil.equalsVerifier(User.class);
        User user1 = new User();
        user1.setId("1");
        user1.setLoginId("Login1");
        User user2 = new User();
        user2.setId(user1.getId());
        user2.setLoginId(user1.getLoginId());
        assertThat(user1).isEqualTo(user2);
        user2.setId("2");
        user2.setLoginId("Login2");
        assertThat(user1).isNotEqualTo(user2);
        user1.setId(null);
        assertThat(user1).isNotEqualTo(user2);
    }

}
