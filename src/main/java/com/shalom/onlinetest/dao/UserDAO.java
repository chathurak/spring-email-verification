package com.shalom.onlinetest.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shalom.onlinetest.entity.User;

@Repository
public class UserDAO implements IUserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		Query<User> query = session.createQuery("from User where email=:userEmail", User.class);
		query.setParameter("userEmail", email);
		
		User user = null;
		try {
			user = query.getSingleResult();
		}catch (Exception e) {
			user = null;
		}
		return user;
	}

	@Override
	public void save(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
		
	}

}
