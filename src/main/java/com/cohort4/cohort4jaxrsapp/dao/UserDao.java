package com.cohort4.cohort4jaxrsapp.dao;

import com.cohort4.cohort4jaxrsapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
	
	private List<User> userList = new ArrayList<>();
	
	public List<User> getAll(){
		
		List<User> users = fakeDbCall();
		
		return users;
	}
	
	public User getAUser(int id) {
		
		List<User> users = fakeDbCall();
		
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public int addUser(User user) {
		
		try {
			if (user != null) {
				userList.add(user);
				return 1;
			} else {
				return 0;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public int updateUser(User user) {
		return 1;
	}
	
	public int deleteUser(User user) {
		try {
			if (user != null) {
				userList.remove(user);
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	
	
	private List<User> fakeDbCall(){
		
		
		userList.add(new User(1,"sahan@bcas.lk", "sahan", "123", "USER"));
		userList.add(new User(2,"sam@gmail.com", "Sammy", "455", "ADMIN"));
		userList.add(new User(3,"anne@yahoo.com", "Anne", "789", "STAFF"));
		userList.add(new User(4,"ron@gmail.com", "Ronald", "455", "STAFF"));
		userList.add(new User(5,"will@yahoo.com", "William", "123", "USER"));
		
		return userList;
	}

}
