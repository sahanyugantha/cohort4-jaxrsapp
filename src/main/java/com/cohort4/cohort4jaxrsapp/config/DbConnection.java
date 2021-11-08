package com.cohort4.cohort4jaxrsapp.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.cj.jdbc.Driver;

public class DbConnection {
	
	private static Logger logger = LogManager.getLogger(DbConnection.class);
	
//	private static final String DB_URL = "jdbc:mysql://localhost:3306/cohort4_db";
//	private static final String DB_USER = "root";
//	private static final String DB_PASSWORD = "root";
	
	//mysql://ba30ae0e1ef3c4:ae58e4a6@us-cdbr-east-04.cleardb.com/heroku_65f96ba3e97ae85?reconnect=true
	
	/*private static final String DB_URL = "jdbc:mysql://us-cdbr-east-04.cleardb.com/heroku_65f96ba3e97ae85";
	private static final String DB_USER = "ba30ae0e1ef3c4";
	private static final String DB_PASSWORD = "ae58e4a6";*/
	
	
	private static final String DB_URL = "jdbc:mysql://us-cdbr-east-04.cleardb.com/heroku_5297fa71ca34055";
	private static final String DB_USER = "b012680d2db006";
	private static final String DB_PASSWORD = "7537c6d3";

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
