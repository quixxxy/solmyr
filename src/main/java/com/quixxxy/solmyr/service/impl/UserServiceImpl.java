package com.quixxxy.solmyr.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quixxxy.solmyr.dao.UserDao;
import com.quixxxy.solmyr.domain.SecurityRole;
import com.quixxxy.solmyr.domain.User;
import com.quixxxy.solmyr.security.SecurityRoles;
import com.quixxxy.solmyr.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("userDaoImpl")
	private UserDao userDao;
	@Autowired
	private UserAssembler assembler;
	@Autowired
	private ProviderManager authenticationManager;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public void addUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Set<SecurityRole> roles = new HashSet<SecurityRole>();
		SecurityRole role = new SecurityRole();
		role.setRoleName(SecurityRoles.ROLE_USER.name());
		roles.add(role);
		user.setSecurityRoles(roles);
		user.setActive(true);
		userDao.addUser(user);
	}

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.getUserByName(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return assembler.getUserSpringObject(user);
	}
	
	@Transactional(readOnly = true)
	public User getUserByName(String username) throws UsernameNotFoundException {
		return userDao.getUserByName(username);
	}

	@Transactional(readOnly = true)
	public Authentication authenticate(User user) {
		UserDetails userDetails = loadUserByUsername(user.getUsername());
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
		return authenticationManager.authenticate(token);
	}
}
