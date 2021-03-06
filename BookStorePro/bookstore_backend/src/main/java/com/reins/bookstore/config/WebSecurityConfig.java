package com.reins.bookstore.config;

import com.reins.bookstore.security.DefaultPasswordEncoder;
import com.reins.bookstore.security.LogoutUtils;
import com.reins.bookstore.security.UnauthorizedEntryPoint;
import com.reins.bookstore.security.filter.TokenAuthenticationFilter;
import com.reins.bookstore.security.filter.TokenLoginFilter;
import com.reins.bookstore.service.BookStoreUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DefaultPasswordEncoder defaultPasswordEncoder;

    @Autowired
    LogoutUtils logoutUtils;

    @Autowired
    BookStoreUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .authenticationEntryPoint(new UnauthorizedEntryPoint())
                .and().authorizeRequests()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .logout().logoutUrl("/logout").logoutSuccessHandler(logoutUtils)
                .and()
                .addFilter(new TokenLoginFilter(authenticationManager()))
                .addFilter(new TokenAuthenticationFilter(authenticationManager()))
                .cors().and()
                .httpBasic();
    }
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(defaultPasswordEncoder);
    }

    @Override
    public void configure(WebSecurity webSecurity ) {
        webSecurity.ignoring().antMatchers("/websocketbot");
    }
}
