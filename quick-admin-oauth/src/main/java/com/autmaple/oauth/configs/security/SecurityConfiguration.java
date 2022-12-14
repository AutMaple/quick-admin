package com.autmaple.oauth.configs.security;

import com.autmaple.oauth.mapper.PermissionMapper;
import com.autmaple.oauth.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserMapper userMapper;
    private final PermissionMapper permissionMapper;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        动态权限控制
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                // 未登录时，进入登录页面
                .formLogin()
                .and()
                .addFilterBefore(dynamicSecurityFilter(), FilterSecurityInterceptor.class);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(userMapper);
    }

    @Bean
    public AccessDecisionManager dynamicAccessDecisionManager() {
        return new DynamicAccessDecisionManager();
    }

    @Bean
    public FilterInvocationSecurityMetadataSource dynamicMetadataSource() {
        return new DynamicSecurityMetadataSource(permissionMapper);
    }

    @Bean
    public Filter dynamicSecurityFilter() {
        return new DynamicSecurityFilter(dynamicMetadataSource(), dynamicAccessDecisionManager());
    }

}
