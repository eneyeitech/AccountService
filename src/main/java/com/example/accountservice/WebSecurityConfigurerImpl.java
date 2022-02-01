package com.example.accountservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// Extending the adapter and adding the annotation
@Configuration
@EnableWebSecurity
public class WebSecurityConfigurerImpl extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    BCryptEncoderConfig b;

    @Autowired
    RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    // Acquiring the builder
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .userDetailsService(userDetailsService) // user store 1
                .passwordEncoder(b.getEncoder());
        auth
                .inMemoryAuthentication() // user store 2
                .withUser("Admin").password("hardcoded").roles("USER")
                .and().passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    // creating a PasswordEncoder that is needed in two places


   /** @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.POST,"/api/auth/signup").permitAll()
                .mvcMatchers("/api/empl/payment").authenticated()
                .and().httpBasic()
                .and()
                .csrf().disable();
    }*/

   public void configure(HttpSecurity http) throws Exception {
       http.httpBasic()
               .authenticationEntryPoint(restAuthenticationEntryPoint) // Handle auth error
               .and()
               .csrf().disable().headers().frameOptions().disable() // for Postman, the H2 console
               .and()
               .authorizeRequests() // manage access
               .mvcMatchers(HttpMethod.POST, "/api/auth/signup").permitAll()
               .mvcMatchers(HttpMethod.POST, "/api/acct/payments").permitAll()
               .mvcMatchers(HttpMethod.PUT, "/api/acct/payments").permitAll()
               .mvcMatchers(HttpMethod.POST, "/api/auth/changepass").authenticated()
               .mvcMatchers("/api/empl/payment").authenticated()
               // other matchers
               .and()
               .sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // no session
   }
}

