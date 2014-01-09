package com.quixxxy.solmyr.dao.impl.jpa;

import org.springframework.stereotype.Repository;

import com.quixxxy.solmyr.dao.UserDao;
import com.quixxxy.solmyr.domain.User;

@Repository
public class JpaUserDaoImpl extends JpaBaseDaoImpl<User, Long> implements
		UserDao {

	public User addUser(User user) {
		return merge(user);
	}

	public User getUserByName(String username) {
		return getEntityManager()
				.createQuery(
						"select user from User user where user.username like :username",
						User.class).setParameter("username", username)
				.getSingleResult();
	}

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}
}
