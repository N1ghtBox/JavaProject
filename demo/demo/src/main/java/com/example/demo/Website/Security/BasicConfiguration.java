//package com.example.demo.Website.Security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
////public class BasicConfiguration extends WebSecurityConfigurerAdapter {
////
////    @Autowired
////    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////        auth
////                .inMemoryAuthentication()
////                .withUser("admin")
////                .password(passwordEncoder().encode("password"))
////                .roles("ADMIN");
////    }
////
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http
////                .formLogin()
////                .loginPage("/login.html")
////                .failureUrl("/login-error.html")
////                .defaultSuccessUrl("/main.html")
////                .and()
////                .logout()
////                .logoutSuccessUrl("/index.html")
////                .and()
////                .sessionManagement()
////                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
////                .maximumSessions(2);
////    }
////
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
////
////
////}