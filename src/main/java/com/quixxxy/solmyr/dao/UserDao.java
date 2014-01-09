package com.quixxxy.solmyr.dao;

import com.quixxxy.solmyr.domain.User;

public interface UserDao {
	
	User addUser(User user);

	User getUserByName(String username);

}
