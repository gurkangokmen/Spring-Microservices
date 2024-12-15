package com.in28minutes.rest.webservices.restfulwebservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
    @Bean 
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(auth->auth.anyRequest().authenticated()); //All requests should be authenticated

        httpSecurity.httpBasic(withDefaults()); //Use Basic Authentication
        httpSecurity.csrf(csrf->csrf.disable()); //Disable CSRF
        return httpSecurity.build(); //Return SecurityFilterChain
    }

}
