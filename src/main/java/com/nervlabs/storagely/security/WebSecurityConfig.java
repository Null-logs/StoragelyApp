package com.nervlabs.storagely.security;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import java.beans.Customizer;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
	@Bean
	PasswordEncoder passwordEncode() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//        http
//        	.csrf(csrf -> csrf.disable())
//        .authorizeHttpRequests((authRequest) -> authRequest.anyRequest().permitAll())
//        .httpBasic(withDefaults());
//        .formLogin(withDefaults());

//		return http.
//				csrf(csrf -> csrf.disable()).authorizeHttpRequests(
//				(authRequest) -> authRequest.requestMatchers(HttpMethod.POST, "/employee/register").permitAll()
////        		.requestMatchers(HttpMethod.GET,"/employee/roles").authenticated()
//		).formLogin(withDefaults()).httpBasic(withDefaults()).build();
		
			http
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests((authorize) -> authorize
						.anyRequest().hasAuthority("USER")
					)
					.httpBasic(withDefaults())
					.formLogin(withDefaults());

				return http.build();
	}
}

//El authority es el que le asignamos al userdetails
