package com.autmaple.oauth.configs.security;

import com.autmaple.oauth.mapper.PermissionMapper;
import com.autmaple.oauth.mapper.UserMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.io.PrintWriter;
import java.util.HashMap;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserMapper userMapper;
    private final PermissionMapper permissionMapper;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login.html");
        // 动态权限控制
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
                        fsi.setAccessDecisionManager(customAccessDecisionManager());
                        fsi.setSecurityMetadataSource(filterInvocationSecurityMetadataSource());
                        return fsi;
                    }
                })
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable();

        // 认证异常
        http.exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            HashMap<String, Object> result = new HashMap<>();
            result.put("code", "111112");
            result.put("msg", authException.getMessage());
            out.write(new ObjectMapper().writeValueAsString(result));
            out.flush();
            out.close();
        });
        // 鉴权异常
        http.exceptionHandling().accessDeniedHandler((request, response, accessDeniedException) -> {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            HashMap<String, Object> result = new HashMap<>();
            result.put("code", "111113");
            result.put("msg", accessDeniedException.getMessage());
            out.write(new ObjectMapper().writeValueAsString(result));
            out.flush();
            out.close();
        });
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(userMapper);
    }

    @Bean
    public CustomAccessDecisionManager customAccessDecisionManager() {
        return new CustomAccessDecisionManager();
    }

    @Bean
    public CustomFilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource() {
        return new CustomFilterInvocationSecurityMetadataSource(permissionMapper);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
