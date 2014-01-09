package com.quixxxy.solmyr.dao.impl.hibernate;

import org.springframework.stereotype.Repository;

import com.quixxxy.solmyr.dao.UserDao;
import com.quixxxy.solmyr.domain.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User, Long> implements UserDao {

	public User addUser(User user) {
		return merge(user);
	}

	public User getUserByName(String username) {
		return (User)getSession().createQuery("from User user where user.username like :username")
				.setString("username", username).uniqueResult();
	}

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}

}
