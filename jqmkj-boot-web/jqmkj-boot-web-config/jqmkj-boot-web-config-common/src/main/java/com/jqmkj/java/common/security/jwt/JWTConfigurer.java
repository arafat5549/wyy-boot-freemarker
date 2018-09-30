package com.jqmkj.java.common.security.jwt;

import com.jqmkj.java.common.config.JqmkjProperties;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final TokenProvider tokenProvider;
    private final JqmkjProperties jqmkjProperties;

    public JWTConfigurer(TokenProvider tokenProvider, JqmkjProperties jqmkjProperties) {
        this.tokenProvider = tokenProvider;
        this.jqmkjProperties = jqmkjProperties;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        JWTFilter customFilter = new JWTFilter(tokenProvider, jqmkjProperties);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
