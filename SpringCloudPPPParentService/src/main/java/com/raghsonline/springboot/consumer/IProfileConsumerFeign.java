package com.raghsonline.springboot.consumer;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.raghsonline.springboot.model.User;

@FeignClient("PPP-PROFILE-SERVICE")
public interface IProfileConsumerFeign {
	
	@GetMapping("/users/")
	public ResponseEntity<List<User>> getAllUsers();
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUser(@RequestParam Long id);
	
	@PostMapping("/users/")
	public ResponseEntity<User> saveUser(@RequestBody User user);

}
