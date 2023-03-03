package com.mafv.notas.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mafv.notas.services.UsuariosService;

@Configuration
public class SecurityConfig{

    @Autowired
    JWTAuthorizationFilter authorizationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager autManager) throws Exception{
        
        JWTAuthenticacionFilter authenticacionFilter = new JWTAuthenticacionFilter();
        authenticacionFilter.setAuthenticationManager(autManager);
        authenticacionFilter.setFilterProcessesUrl("/login");

        http
            .csrf().disable()
            .authorizeRequests()
            .anyRequest()
            .authenticated()
        .and()
            .httpBasic()
        .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .addFilter(authenticacionFilter)
            .addFilterBefore(authenticacionFilter, UsernamePasswordAuthenticationFilter.class)
            ;
        
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        //return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    UsuariosService myUserService(){
        return new UsuariosService();
    }

    @Bean
    AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
            .userDetailsService(myUserService())
            .passwordEncoder(passwordEncoder())
            .and()
            .build();
    }


}


