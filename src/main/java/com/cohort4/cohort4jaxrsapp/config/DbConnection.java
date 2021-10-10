package com.cohort4.cohort4jaxrsapp.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.cj.jdbc.Driver;

public class DbConnection {
	
	private static Logger logger = LogManager.getLogger(DbConnection.class);
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/cohort4_db";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root";
	

	private static DbConnection newInstance = null;
	
	private DbConnection() {
		
	}
	
	public static DbConnection getInstance() {
		if (newInstance == null) {
			newInstance =  new DbConnection();
		}
		return newInstance;
	}
	
	
	public Connection getConnection() {
		
		try {
			
			//Method 1
			//Class.forName("com.mysql.cj.jdbc.Driver");
			//Connection conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
			
			//Method 2
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			Connection conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
			
			logger.info("DB SUCCESS :  Connection Successful");
			return conn;			
			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.hashCode()+" DB ERROR :  Connection failed - "+e.getMessage());
			return null;
		}
		
	}

}
