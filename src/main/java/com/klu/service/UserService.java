package com.klu.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.model.User;
import com.klu.repo.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository repo;
	
	@Autowired
	JWTService jwtService;
	
	public Object signup(User u1) {
		Map<String, Object> response=new HashMap<>();
		try {
			User u2=new User();
			u2.setUsername(u1.getUsername());
			u2.setPassword(u1.getPassword());
			u2.setRole(0);
			if(repo.findByUsername(u2.getUsername())==null) {
				repo.save(u2);
				response.put("code", 200);
				response.put("message","user registered");
			}else {
				response.put("code", 501);
				response.put("message","user already exist");
			}
		}catch(Exception e){
			response.put("code", 500);
			response.put("message",e.getMessage());
		}
		return response;
		
	}
	public Object signin(User u1) {
		Map<String,Object> response = new HashMap<>();

	    try {
	        User user = repo.findByUsername(u1.getUsername());

	        System.out.println("REQUEST USERNAME = " + u1.getUsername());
	        System.out.println("REQUEST PASSWORD = " + u1.getPassword());

	        if (user == null) {
	            System.out.println("USER NOT FOUND");
	        } else {
	            System.out.println("DB USERNAME = " + user.getUsername());
	            System.out.println("DB PASSWORD = " + user.getPassword());
	        }

	        if(user == null || !u1.getPassword().equals(user.getPassword())) {
	            response.put("code", 501);
	            response.put("message", "Authentication failed");
	        } else {
	            response.put("code", 200);
	            response.put("jwt", jwtService.generateJWT(user));
	        }

	    } catch (Exception e) {
	        response.put("code", 500);
	        response.put("message", e.getMessage());
	    }

	    return response;
	}
	
	
}
