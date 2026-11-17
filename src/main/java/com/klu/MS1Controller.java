package com.klu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klu.model.User;
import com.klu.service.UserService;

@RestController
@RequestMapping("/ms1")
public class MS1Controller {

	@GetMapping("/")
	public String test() {
		return "MS1.1 Working";
	}
	
	@GetMapping("/add")
	public String add(@RequestParam("a") int a,@RequestParam("b") int b) {
		return "MS1.1 Addition:-" + (a+b);
	}
	@Autowired
	UserService service;
	@PostMapping("/signup")
	public Object signup(@RequestBody User u1) {
		return service.signup(u1);
	}
	@PostMapping("/signin")
	public Object signin(@RequestBody User u1) {
		return service.signin(u1);
	}
	
}
