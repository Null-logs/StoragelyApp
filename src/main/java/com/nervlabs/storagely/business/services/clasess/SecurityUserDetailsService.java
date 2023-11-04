package com.nervlabs.storagely.business.services.clasess;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nervlabs.storagely.persistence.IEmployeeRepository;
import com.nervlabs.storagely.security.SecurityUser;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

	 private final IEmployeeRepository userRepository;

	    public SecurityUserDetailsService(IEmployeeRepository userRepository) {
	        this.userRepository = userRepository;
	    }

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	        var user = this.userRepository.findByUsername(username);

	        if(user.isPresent()){
	           var c =  new SecurityUser(user.get());
           
	           return c;
	        }

	        throw new UsernameNotFoundException("User not found: " + username);
	    }

}
