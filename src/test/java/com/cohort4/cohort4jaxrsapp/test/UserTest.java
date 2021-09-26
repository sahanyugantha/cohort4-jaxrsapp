package com.cohort4.cohort4jaxrsapp.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.cohort4.cohort4jaxrsapp.dao.UserDao;
import com.cohort4.cohort4jaxrsapp.model.User;

public class UserTest {
	
	//Arrange.
	int unexpected = 0;
	
	@Test
	public void testUserDbCall() {
		UserDao userDao = new UserDao();
		List<User> users = userDao.getAll();
		int actual = users.size();
		
		assertNotEquals(unexpected, actual);
	}
	
	//Arrange
	int userId = 5;
	
	@Test
	public void testAUser() {
		UserDao userDao = new UserDao();
		User user = userDao.getAUser(userId);
		
		assertNotNull(user);
	}
	
	
	//Arrange
	User user = new User(6,"test@bcas.lk", "test", "123", "USER");
	int expected = 1;
	
	@Test
	public void testAddUser() {
		UserDao userDao = new UserDao();
		int actual = userDao.addUser(user);
		
		assertEquals(expected, actual);
	}

}
