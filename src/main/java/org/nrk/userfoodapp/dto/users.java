package org.nrk.userproductapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.nrk.userproductapp.dto.User;

public class UserDao {
	EntityManager manager = Persistence.createEntityManagerFactory("dev").createEntityManager();
	EntityTransaction transaction = manager.getTransaction();

	public User saveUser(User user) {
		manager.persist(user);
		transaction.begin();
		transaction.commit();
		return user;
	}

	public User updateUser(User user) {
		manager.merge(user);
		transaction.begin();
		transaction.commit();
		return user;
	}

	public User findUserById(int id) {
		return manager.find(User.class, id);
	}

	public boolean deleteUser(int id) {
		User u = findUserById(id);
		if (u != null) {
			manager.remove(u);
			transaction.begin();
			transaction.commit();
			return true;
		}
		return false;
	}

	public User verifyUser(long phone,String password) {
		String qry = "select u from User u where u.phone=?1 and u.password=?2";
		Query q = manager.createQuery(qry);
		q.setParameter(1, phone);
		q.setParameter(2,password);
		try {
			return (User) q.getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
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
}
package org.nrk.userproductapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.nrk.userproductapp.dto.Product;
import org.nrk.userproductapp.dto.User;

public class ProductDao {
	EntityManager manager = Persistence.createEntityManagerFactory("dev").createEntityManager();
	EntityTransaction transaction = manager.getTransaction();
	
	public Product saveProduct(Product product,int user_id) {
		User u = manager.find(User.class, user_id);
		if(u!=null) {
			u.getProducts().add(product);
			product.setUser(u);
			manager.persist(u);
			transaction.begin();
			transaction.commit();
			return product;
		}
		return null;
	}
	public Product updateProduct(Product product,int user_id) {
		User u = manager.find(User.class, user_id);
		if(u!=null) {
			u.getProducts().add(product);
			product.setUser(u);
			manager.merge(u);
			transaction.begin();
			transaction.commit();
			return product;
		}
		return null;
	}
	
	public Product findProductById(int id) {
		return manager.find(Product.class, id);
	}
	
	public boolean deleteProduct(int id) {
		Product p =findProductById(id);
		if(p!=null) {
			manager.remove(p);
			transaction.begin();
			transaction.commit();
			return true;
		}
		return false;
	}
	
	public List <Product> findProductsByUserId(int user_id){
		String qry= "select u.products from User u where u.id=?1";
		Query q = manager.createQuery(qry);
		q.setParameter(1, user_id);
		return q.getResultList();
	}

}

