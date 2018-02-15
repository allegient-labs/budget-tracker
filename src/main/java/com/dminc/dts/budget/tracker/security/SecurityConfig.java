package com.dminc.dts.budget.tracker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    GoogleAuthenticationProvider googleAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/callback").permitAll()  // Allow users to reach login endpoint
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new GoogleAuthenticationFilter(authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class);
        http.httpBasic().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(googleAuthenticationProvider);
    }
}
