package com.jqmkj.java.common.security.service;

import com.jqmkj.java.common.config.JqmkjProperties;
import com.jqmkj.java.common.persistence.domain.BaseEntity;
import com.jqmkj.java.common.security.*;
import com.jqmkj.java.modules.sys.domain.User;
import com.jqmkj.java.modules.sys.service.UserService;
import com.jqmkj.java.util.PublicUtil;
import com.jqmkj.java.util.StringUtil;
import com.jqmkj.java.util.base.Assert;
import com.jqmkj.java.util.exception.RuntimeMsgException;
import com.jqmkj.java.vo.sys.UserVo;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

    private final UserService userService;

    private final CacheManager cacheManager;
    private final JqmkjProperties jqmkjProperties;

    public UserDetailsService(UserService userService, CacheManager cacheManager, JqmkjProperties jqmkjProperties) {
        this.userService = userService;
        this.cacheManager = cacheManager;
        this.jqmkjProperties = jqmkjProperties;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);
        Optional<User> userFromDatabase = userService.findOneByLoginId(login);
        return userFromDatabase.map(user -> {
            Assert.assertIsTrue(BaseEntity.FLAG_NORMAL.equals(user.getStatus()), "用户 " + login + " 登录信息已被锁定");
            Assert.assertIsTrue(jqmkjProperties.getUserType().contains(user.getType()),"用户 " + login + " 无法登录" );

            List<GrantedAuthority> grantedAuthorities = Lists.newArrayList(new SimpleGrantedAuthority("user"));
            if (SecurityAuthUtil.isAdmin(user.getId())) {
                grantedAuthorities.add(new SimpleGrantedAuthority(AuthoritiesConstants.ADMIN));
            }
            SecurityUtil.getModuleList(user.getId()).forEach(authority -> {
                if (PublicUtil.isNotEmpty(authority.getPermission())) {
                    Lists.newArrayList(authority.getPermission().split(StringUtil.SPLIT_DEFAULT)).forEach(p -> {
                        grantedAuthorities.add(new SimpleGrantedAuthority(p));
                    });
                }
            });
            return new UserPrincipal(user.getId(), login,
                    user.getPassword(),
                    grantedAuthorities);
        }).orElseThrow(() -> new RuntimeMsgException("用户 " + login + " 不存在"));
    }
}
