package org.nrk.userfoodapp.controller;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.nrk.userfoodapp.dao.FoodDao;
import org.nrk.userfoodapp.dao.UserDao;
import org.nrk.userfoodapp.dto.Food;
import org.nrk.userfoodapp.dto.User;

public class FoodOrder {
	static Scanner s = new Scanner(System.in);
	static UserDao uDao = new UserDao();
	static FoodDao fDao = new FoodDao();
	
	

	public static void main(String[] args) {
		System.out.println("1.Save the User");
		System.out.println("2.Verify the User (Email & Password)");
		System.out.println("3.Add the Food Order");
		System.out.println("4.Update the User");
		System.out.println("5.Update the FooodOrder");
		System.out.println("6.Fetch the FoodOrders By User ID");
		System.out.println("7.Fetch the FoodOrders By User Email and Password");
		System.out.println("Enter Your Choice");
		int choice = s.nextInt();
		switch (choice) {
		case 1:
			saveUser();
			break;

		case 2:
			verifyUser();
			break;

		case 3:
			addFoodOrder();
			break;
		case 4:
			updateUser();
			break;
		case 5:
			updateFoodOrder();
			break;
		case 6:
			FetchFoodOrderByUserID();
			break;
		case 7:
			FetchFoodOrderByUserEmailPass();
			break;

		default:
			break;
		}

	}

	private static void FetchFoodOrderByUserEmailPass() {
		System.out.println("Enter the Email & Password");
		String email = s.next();
		String password = s.next();
		List<Food> f =fDao.findProductsByEmailPass(email,password);
		if(f!=null) {
			System.out.println(f);
		}		
	}

	private static void FetchFoodOrderByUserID() {
		System.out.println("Enter the ID to Fetch Order by User ID");
		int id = s.nextInt();
		List<Food> f =fDao.findProductsByUserId(id);
		if(f!=null) {
			System.out.println(f);
		}
	}

	private static void addFoodOrder() {
		System.out.println("Enter Your Id");
		int fid = s.nextInt();
		System.out.println("Enter the Food Name,price");
		String name = s.next();
		double price = s.nextDouble();
		String del=s.next();
		String ord = s.next();
		Food f = new Food();
		f.setPrice(price);
		f.setItem_name(name);
		f.setDel_time(del);
		f.setOrdered_time(ord);
		f = fDao.saveFoodOrder(f, fid);
		if (f != null) {
			System.out.println(f);
		}
	}

	private static void updateFoodOrder() {
		System.out.println("Enter Food Id");
		int fid = s.nextInt();
		EntityManager manager = Persistence.createEntityManagerFactory("dev").createEntityManager();
		Food f = manager.find(Food.class,fid);
		if(f!=null) {
			System.out.println("Enter the Food Name,price");
			String name = s.next();
			double price = s.nextDouble();
			String del=s.next();
			String ord = s.next();
			f.setPrice(price);
			f.setItem_name(name);
			f.setDel_time(del);
			f.setOrdered_time(ord);
			f = fDao.updateFoodOrder(f, fid);
			if (f != null) {
				System.out.println(f);
			}
		}

	}

	private static void verifyUser() {
		System.out.println("Enter Your Email & Password to Verify");
		String email = s.next();
		String password = s.next();
		User u = uDao.verifyUser(email, password);
		if (u != null) {
			System.out.println(u);
		} else {
			System.err.println("INVALID EMAIL OR PASSWORD");
		}
	}

	private static void saveUser() {
		System.out.println("Enter the Name, Gender, Email, Phone & Password to Register the User");
		String name = s.next();
		String gender = s.next();
		String email = s.next();
		long phone = s.nextLong();
		String password = s.next();
		User u = new User();
		u.setName(name);
		u.setGender(gender);
		u.setEmail(email);
		u.setPhone(phone);
		u.setPassword(password);
		u = uDao.saveUser(u);
		if (u != null) {
			System.out.println("User Register");
		}

	}

	private static void updateUser() {
		System.out.println("Enter Your id");
		int id = s.nextInt();
		EntityManager manager = Persistence.createEntityManagerFactory("dev").createEntityManager();
		User u = manager.find(User.class,id);
		if(u!=null) {
			System.out.println("Enter the Name, Gender, Email, Phone & Password to Update the User");
			String name = s.next();
			String gender = s.next();
			String email = s.next();
			long phone = s.nextLong();
			String password = s.next();
			u.setName(name);
			u.setGender(gender);
			u.setEmail(email);
			u.setPhone(phone);
			u.setPassword(password);
			uDao.updateUser(u);
			if (u != null) {
				System.out.println("User Updated");
			}
		}

	}

}
