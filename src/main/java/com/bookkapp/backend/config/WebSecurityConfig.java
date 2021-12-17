package com.bookkapp.backend.config;

import com.bookkapp.backend.Services.UserActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@ComponentScan("com.bookkapp.backend.config")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;

    @Bean
    public UserDetailsService mongoUserDetails() {
        return new UserActions();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserDetailsService userDetailsService = mongoUserDetails();
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/api/books/**").hasAuthority("ADMIN").anyRequest()
                .authenticated().and().csrf().disable().formLogin().successHandler(customizeAuthenticationSuccessHandler)
                .loginPage("/login").failureUrl("/login?error=true")
                .usernameParameter("userEmail")
                .passwordParameter("userPwd")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/api/books").and().exceptionHandling();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**\", \"/static/**\", \"/css/**\", \"/js/**\", \"/images/**");
    }

}
