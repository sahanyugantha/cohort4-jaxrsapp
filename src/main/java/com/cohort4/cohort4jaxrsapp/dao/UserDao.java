package com.cohort4.cohort4jaxrsapp.dao;

import com.cohort4.cohort4jaxrsapp.config.DbConnection;
import com.cohort4.cohort4jaxrsapp.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDao {
	
	private Logger logger = LogManager.getLogger(UserDao.class);
	
	private List<User> userList = new ArrayList<>();
	
	/**
	 * Get all the users.
	 * @return
	 */
	public List<User> getAll(){
		
		
		List<User> users = getUsersFromDb();
		
		return users;
	}
	
	/**
	 * Get a user by user id.
	 * @param id
	 * @return
	 */
	public User getAUser(int id) {
		
		List<User> users = getUsersFromDb();
		
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	/**
	 * Login checker method.
	 * @param email
	 * @param password
	 * @return
	 */
	public User userAuth(String email, String password) {
		Connection connection = DbConnection.getInstance().getConnection();
		
		try {
			
			String encryptedPassword = Sha1Encrpt(password);
			
			//Prepare SQL query.
			String sql = "SELECT * FROM `tbl_user` WHERE `email`=? AND `password`=?";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, encryptedPassword);
			
			ResultSet rs = stmt.executeQuery();
			
			int rows = 0;
			User user = new User();
			while(rs.next()) {
				rows++;
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
			}
			
			if(rows == 1) {
				return user;
			} else {
				return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		}
		
	}
	
	
	/**
	 * Add user into the system.
	 * @param user
	 * @return
	 */
	public int addUser(User user) {
		
		Connection connection = DbConnection.getInstance().getConnection();
		
		try {
			
			String password =  user.getPassword();
			String encryptedPassword = Sha1Encrpt(password);
			
			
			//Prepare SQL query.
			String sql = "INSERT INTO `tbl_user` (`username`, `email`, `password`, `role`) "
					+ "VALUES (?, ?, ?, ?);";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, encryptedPassword);
			stmt.setString(4, user.getRole());
			
			int response = stmt.executeUpdate();
			connection.close();
			return response;
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("SQL ERROR :  Could not insert data - "+e.getMessage());
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
	
	
	public List<User> getUsersFromDb(){
	
		List<User> userList = new ArrayList<>();
		
		//Create connection instance.
		Connection conn = DbConnection.getInstance().getConnection();
		
		//Prepare the query.
		String sql = "SELECT * FROM `tbl_user`;";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet resultSet = stmt.executeQuery();
						
			while(resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setUsername(resultSet.getString("username"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				user.setRole(resultSet.getString("role"));
				
				userList.add(user);
			}
			conn.close();
			return userList;
		} catch (SQLException e) {
			logger.error("DB ERROR :  Could not access data - "+e.getMessage());
			return null;
		}
	}
	
	/**
	 * SHA-1 encryption method
	 * @param tobeEncrypted
	 * @return
	 */
	public String Sha1Encrpt(String tobeEncrypted) {
		try {
			byte[] passwordArr = tobeEncrypted.getBytes(StandardCharsets.UTF_8);
			MessageDigest sha1Encrypt = MessageDigest.getInstance("SHA-1");
			byte[] encrptPassword = sha1Encrypt.digest(passwordArr);
			
			String encryptedString = Base64.getEncoder().encodeToString(encrptPassword);
			
			return encryptedString;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	private List<User> fakeDbCall(){
		
		//Log
		logger.fatal("This is a FATAL log");
		logger.error("This is a ERROR log");
		logger.warn("This is a WARN log");
		logger.info("This is a INFO log");
		logger.debug("This is a DEBUG log");
		logger.trace("This is a TRACE log");
		
		userList.add(new User(1,"sahan@bcas.lk", "sahan", "123", "USER"));
		userList.add(new User(2,"sam@gmail.com", "Sammy", "455", "ADMIN"));
		userList.add(new User(3,"anne@yahoo.com", "Anne", "789", "STAFF"));
		userList.add(new User(4,"ron@gmail.com", "Ronald", "455", "STAFF"));
		userList.add(new User(5,"will@yahoo.com", "William", "123", "USER"));
		
		return userList;
	}

}
