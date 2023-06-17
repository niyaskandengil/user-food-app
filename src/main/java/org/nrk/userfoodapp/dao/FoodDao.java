package org.nrk.userfoodapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.nrk.userfoodapp.dto.Food;
import org.nrk.userfoodapp.dto.User;

public class FoodDao {
	EntityManager manager = Persistence.createEntityManagerFactory("dev").createEntityManager();
	EntityTransaction transaction = manager.getTransaction();

	public Food saveFoodOrder(Food food, int user_id) {
		User u = manager.find(User.class, user_id);
		if (u != null) {
			u.getFood().add(food);
			food.setUser(u);
			manager.persist(food);
			transaction.begin();
			transaction.commit();
			return food;
		}
		return null;
	}

	public Food updateFoodOrder(Food food, int user_id) {
		User u = manager.find(User.class, user_id);
		if (u != null) {
			u.getFood().add(food);
			food.setUser(u);
			manager.merge(food);
			transaction.begin();
			transaction.commit();
			return food;
		}
		return null;
	}

	public List<Food> findProductsByUserId(int user_id) {
		String qry = "select u.food from User u where u.id=?1";
		Query q = manager.createQuery(qry);
		q.setParameter(1, user_id);
		return q.getResultList();
	}

	public List<Food> findProductsByEmailPass(String email, String password) {
		String qry = "select u.food from User u where u.email=?1 and u.password=?2";
		Query q = manager.createQuery(qry);
		q.setParameter(1, email);
		q.setParameter(2, password);
		return q.getResultList();
	}
}
