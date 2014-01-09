package com.quixxxy.solmyr.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quixxxy.solmyr.domain.SecurityRole;
import com.quixxxy.solmyr.domain.User;

@Service
public class UserAssembler {

	@Transactional(readOnly = true)
	public UserDetails getUserSpringObject(User user) {
		String username = user.getUsername();
		String password = user.getPassword();
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (SecurityRole role: user.getSecurityRoles()){
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		return new org.springframework.security.core.userdetails.User(username,
				password, authorities);
	}
}
