package com.nervlabs.storagely;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.nervlabs.storagely.business.commons.enums.Roles;
import com.nervlabs.storagely.domain.entites.EmployeeEntity;
import com.nervlabs.storagely.persistence.IEmployeeRepository;

@Component
public class Runner implements CommandLineRunner {

	private final IEmployeeRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	public Runner(IEmployeeRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void run(String... args) throws Exception {
		if (this.userRepository.count() == 0) {
//            var encoders = PasswordEncoderFactories.createDelegatingPasswordEncoder();
			this.userRepository.saveAll(List.of(EmployeeEntity.builder().username("Null").password(passwordEncoder.encode("1234")).rol(Roles.STORER).build()));
		}
	}
}