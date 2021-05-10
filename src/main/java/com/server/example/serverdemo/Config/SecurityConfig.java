package com.server.example.serverdemo.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .antMatcher("/v1/**").authorizeRequests()
                .antMatchers("/v1/health-check").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login();
    }
}
