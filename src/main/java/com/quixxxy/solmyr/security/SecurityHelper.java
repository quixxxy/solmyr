package com.quixxxy.solmyr.security;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public final class SecurityHelper {

	private SecurityHelper() {
	}

	public static UserDetails getUserDetails() {
		SecurityContext ctx = SecurityContextHolder.getContext();
		Object userDetails = ctx.getAuthentication().getPrincipal();
		if (userDetails != null && userDetails instanceof UserDetails) {
			return (UserDetails) userDetails;
		}
		throw new IllegalArgumentException("There is no current user");
	}

	public static String getUserName() {
		return getUserDetails().getUsername();
	}
}
