package com.bgmiastoto.configuration;

import com.bgmiastoto.services.ApplicationUserService;
import com.bgmiastoto.services.SocialUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final ApplicationUserService applicationUserService;

    private final SocialUserService socialUserService;

    private final BCryptPasswordEncoder cryptPasswordEncoder;

    @Autowired
    public SpringSecurityConfig(ApplicationUserService applicationUserService, SocialUserService socialUserService, BCryptPasswordEncoder cryptPasswordEncoder) {
        this.applicationUserService = applicationUserService;
        this.socialUserService = socialUserService;
        this.cryptPasswordEncoder = cryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.applicationUserService).passwordEncoder(cryptPasswordEncoder);
        auth.userDetailsService(this.socialUserService).passwordEncoder(cryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
             .authorizeRequests()
                .antMatchers("/", "/about-us", "/users/**", "/categories/**", "/places/all", "/map", "/bootstrap/**", "/jquery/**", "/css/*", "/js/*", "/connect/**").permitAll()
                .antMatchers("/places/**", "/events/**").access("hasRole('ADMIN') OR hasRole('USER')")
                .anyRequest().authenticated()
            .and()
                .formLogin().loginPage("/users/login").permitAll()
                .usernameParameter("username")
                .passwordParameter("password")
            .and()
                .rememberMe()
                .rememberMeCookieName("RememberMe")
                .rememberMeParameter("rememberMe")
                .key("SecretKey")
                .alwaysRemember(true)
            .and()
                .logout().logoutSuccessUrl("/users/login").permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/users/logout")).permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/unauthorized")
            .and()
                .csrf().disable();
    }
}
