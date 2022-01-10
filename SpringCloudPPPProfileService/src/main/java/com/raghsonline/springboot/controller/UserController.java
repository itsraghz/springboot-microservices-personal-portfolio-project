package com.raghsonline.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raghsonline.springboot.entity.User;
import com.raghsonline.springboot.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUsers() {
		log.info("UserController - getAllUsers() invoked!");
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id) {
		log.info("UserController - getUser(id) invoked with id="+id);
		return ResponseEntity.ok(userService.getUser(id));
	}
	
	@PostMapping("/")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		log.info("UserController - saveUser(user) invoked with user="+user);
		return ResponseEntity.ok(userService.saveUser(user));
	}
}
