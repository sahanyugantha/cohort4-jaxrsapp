package com.cohort4.cohort4jaxrsapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cohort4.cohort4jaxrsapp.dao.UserDao;
import com.cohort4.cohort4jaxrsapp.model.User;
import com.google.gson.Gson;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user")
public class UserResource {
	
	private Gson gson = new Gson();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getItems() {
		UserDao userDao = new UserDao();
		List<User> userList = userDao.getAll();
		
		String jsonString = gson.toJson(userList);
		
		return Response
				.status(200)
				.entity(jsonString)
				.build();
	}
	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAnItem(@PathParam("id") int id) {
		UserDao userDao = new UserDao();
		User user = userDao.getAUser(id);
		if(user != null) {
			String jsonString = gson.toJson(user);
			return Response
					.status(200)
					.entity(jsonString)
					.build();
		} else {
			Map<String, String> errorMsg = new HashMap<>();
			errorMsg.put("ERROR", "Inavlid user id");
			
			String errorString = gson.toJson(errorMsg);
			return Response
					.status(400)
					.entity(errorString)
					.build();
		}
	}
	
	@Path("/login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(String input_data) {
		
		Gson gson = new Gson();
		User input_user = gson.fromJson(input_data, User.class);
		
		UserDao userDao = new UserDao();
		User user = userDao.userAuth(input_user.getEmail(), input_user.getPassword());
		
		if(user != null) {
			Map<String, String> msg = new HashMap<>();
			msg.put("SUCCESS", "Successfully logged into the system");
			String jsonString = gson.toJson(msg);
			
			return Response
					.status(200)
					.entity(jsonString)
					.build();
		} else {
			Map<String, String> msg = new HashMap<>();
			msg.put("ERROR", "please enter valid login details");
			String jsonString = gson.toJson(msg);
			
			return Response
					.status(400)
					.entity(jsonString)
					.build();
		}
		
	}
	
	/*
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addItem(@FormParam("email") String email,
							@FormParam("username") String username,
							@FormParam("password") String password,
							@FormParam("role") String role) {
		
		Gson gson = new Gson();
		
		User user = new User();
		user.setEmail(email);
		user.setUsername(username);
		user.setPassword(password);
		user.setRole(role);
		
		UserDao userDao = new UserDao();
		int res = userDao.addUser(user);		
		
		
		if(res > 0) {
			Map<String, String> msg = new HashMap<>();
			msg.put("SUCCESS", "user record has been added successfully");
			String jsonString = gson.toJson(msg);
			
			return Response
					.status(200)
					.entity(jsonString)
					.build();
		} else {
			Map<String, String> msg = new HashMap<>();
			msg.put("ERROR", "please enter valid information");
			String jsonString = gson.toJson(msg);
			
			return Response
					.status(400)
					.entity(jsonString)
					.build();
		}
					
	} */
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON) // request data type
	@Produces(MediaType.APPLICATION_JSON)
	public Response addItem(String jsonData) {
	
		Gson gson = new Gson();
		User user = gson.fromJson(jsonData, User.class); // converting json string to java class.
		
		UserDao userDao = new UserDao();
		int res = userDao.addUser(user);
		
		if(res > 0) {
			Map<String, String> msg = new HashMap<>();
			msg.put("SUCCESS", "user record has been added successfully");
			String jsonString = gson.toJson(msg);
			
			return Response
					.status(200)
					.entity(jsonString)
					.build();
		} else {
			Map<String, String> msg = new HashMap<>();
			msg.put("ERROR", "please enter valid information");
			String jsonString = gson.toJson(msg);
			
			return Response
					.status(400)
					.entity(jsonString)
					.build();
		}
	}
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem() {
		return "Update a user";
	}
	
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem() {
		return "Delete a user";
	}
	
	//LOG4J 
	//LOGBACK
	//SLF4J 

}
