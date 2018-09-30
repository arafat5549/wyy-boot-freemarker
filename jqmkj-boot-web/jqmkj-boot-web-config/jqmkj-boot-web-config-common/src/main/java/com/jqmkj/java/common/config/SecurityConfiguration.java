package com.jqmkj.java.common.config;

import com.jqmkj.java.common.base.BaseInit;
import com.jqmkj.java.common.security.*;
import com.jqmkj.java.common.security.jwt.JWTConfigurer;
import com.jqmkj.java.common.security.jwt.TokenProvider;
import com.jqmkj.java.common.security.service.InvocationSecurityMetadataSourceService;
import com.jqmkj.java.util.JedisUtil;
import com.jqmkj.java.util.PublicUtil;
import com.jqmkj.java.util.domain.GlobalJedis;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.Resource;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@BaseInit
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Resource
    CustomizeAccessDecisionManager customizeAccessDecisionManager;
    @Resource
    InvocationSecurityMetadataSourceService invocationSecurityMetadataSourceService;
    @Resource
    private JqmkjProperties jqmkjProperties;
    @Resource
    private Http401UnauthorizedEntryPoint http401UnauthorizedEntryPoint;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private TokenProvider tokenProvider;
    @Resource
    private CorsFilter corsFilter;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Resource
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        try {
            auth.userDetailsService(userDetailsService)
                    .passwordEncoder(passwordEncoder());
        } catch (Exception e) {
            throw new BeanInitializationException("Security configuration failed", e);
        }
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .antMatchers("/content/**")
                .antMatchers("/statics/**")
                .antMatchers("/test/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String adminPath = jqmkjProperties.getAdminPath();

        String[] permissAll = new String[SecurityConstants.authorizePermitAll.length+SecurityConstants.authorizeAdminPermitAll.length];

        for (int i = 0; i < SecurityConstants.authorizePermitAll.length; i++) {
            permissAll[i] = SecurityConstants.authorizePermitAll[i];
        }
        for (int i = SecurityConstants.authorizePermitAll.length,j=0; i < permissAll.length; i++,j++) {
            permissAll[i] = adminPath+SecurityConstants.authorizeAdminPermitAll[j];
        }


        http.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(http401UnauthorizedEntryPoint)
                .and()
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(jqmkjProperties.getAdminPath(SecurityConstants.loginUrl)).permitAll()
                .antMatchers(jqmkjProperties.getAdminPath(SecurityConstants.authLogin)).permitAll()
                .antMatchers(jqmkjProperties.getAdminPath(SecurityConstants.logoutUrl)).permitAll()
                .antMatchers(permissAll).permitAll()
                .antMatchers(jqmkjProperties.getAdminPath("/**")).authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
                        fsi.setSecurityMetadataSource(securityMetadataSource());
                        return fsi;
                    }
                }).accessDecisionManager(customizeAccessDecisionManager)
                .antMatchers("/management/**").hasAuthority(AuthoritiesConstants.ADMIN)
                .and()
                .apply(securityConfigurerAdapter());

    }

    @Bean
    public FilterInvocationSecurityMetadataSource securityMetadataSource() {
        return invocationSecurityMetadataSourceService;
    }

    private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(tokenProvider, jqmkjProperties);
    }

    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }


    public void afterPropertiesSet() {
        SecurityUtil.clearUserJedisCache();
        JedisUtil.removeSys(GlobalJedis.RESOURCE_MODULE_DATA_MAP);
        invocationSecurityMetadataSourceService.getResourceMap();
    }


    public static void main(String[] args) {
        String ret = new BCryptPasswordEncoder().encode("admin");
        System.out.println(ret);
    }
}
