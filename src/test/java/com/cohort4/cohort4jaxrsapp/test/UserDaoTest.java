package com.cohort4.cohort4jaxrsapp.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.cohort4.cohort4jaxrsapp.dao.UserDao;
import com.cohort4.cohort4jaxrsapp.model.User;

public class UserDaoTest {
	
	
	
	private int expected = 3;
	
	public void testGetUsers() {
		UserDao userDao = new UserDao();
		List<User> users = userDao.getUsersFromDb();
		int actual = users.size();
		
		assertEquals(expected, actual);
	}
	
	
	
	 
	
	public void testAddUser() {
		
		int expected = 1;
		
		User user = new User();
		user.setUsername("test");
		user.setEmail("test@test.com");
		user.setPassword("000");
		user.setRole("STAFF");
		
		
		UserDao userDao = new UserDao();
		int actual = userDao.addUser(user);
				
		assertEquals(expected, actual);
	
	}
	
	@Test
	public void testLogin() {
	
		String email = "sachithra@bcas.lk";
		String password = "123456abc";
		
		UserDao userDao = new UserDao();
		User actual =  userDao.userAuth(email, password);
		
		assertNotNull(actual);
		
	}

}
