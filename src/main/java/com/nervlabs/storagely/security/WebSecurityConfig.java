package com.nervlabs.storagely.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import com.nervlabs.storagely.persistence.IEmployeeRepository;

@Configuration
public class WebSecurityConfig {

	@Autowired
	IEmployeeRepository userRepository;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		//TODO: IMPLEMENTAR EL LOGOUT
		
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests((authorize) -> authorize
						.requestMatchers("/assets/**").permitAll()
						.requestMatchers("/swagger-ui/**").permitAll()
						.requestMatchers("/access/**").permitAll()
						.requestMatchers("/storer/**").permitAll()
						.requestMatchers("/storer/view/**").permitAll()
						.anyRequest().hasAuthority("USER"))
				.httpBasic(withDefaults()).formLogin(form -> form.loginPage("/login.html").permitAll())
				.securityContext((securityContext) -> securityContext
						.securityContextRepository(securityContextRepository()).requireExplicitSave(true));

		return http.build();
	}

	@Bean
	SecurityContextRepository securityContextRepository() {
		return new HttpSessionSecurityContextRepository();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	UserDetailsService userDetailsService() {
		return new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				var user = userRepository.findByUsername(username);

				if (user.isPresent()) {
					var c = new SecurityUser(user.get());
					return c;
				}
				throw new UsernameNotFoundException("User not found: " + username);
			}
		};
	}

	@Bean
	AuthenticationManager authenticationManager() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());

		return new ProviderManager(authenticationProvider);
	}

}

//El authority es el que le asignamos al userdetails
