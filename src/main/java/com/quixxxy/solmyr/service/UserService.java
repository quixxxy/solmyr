package com.quixxxy.solmyr.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.quixxxy.solmyr.domain.User;

public interface UserService extends UserDetailsService {

	void addUser(User user);

	Authentication authenticate(User user) throws UsernameNotFoundException;
	
	User getUserByName(String username) throws UsernameNotFoundException;
}
