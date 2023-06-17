package org.nrk.userfoodapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.nrk.userfoodapp.dto.User;

public class UserDao {
	EntityManager manager = Persistence.createEntityManagerFactory("dev").createEntityManager();
	EntityTransaction transaction = manager.getTransaction();
	
	public User saveUser(User user) {
		manager.persist(user);
		transaction.begin();
		transaction.commit();
		return user;
	}
	
	public User verifyUser(String email, String password) {
		String qry = "select u from User u where u.email=?1 and u.password=?2";
		Query q = manager.createQuery(qry);
		q.setParameter(1, email);
		q.setParameter(2, password);
		try {
			return (User) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public User updateUser(User user) {
		manager.merge(user);
		transaction.begin();
		transaction.commit();
		return user;
	}
}
