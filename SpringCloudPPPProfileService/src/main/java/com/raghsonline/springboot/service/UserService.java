package com.raghsonline.springboot.service;

import java.util.List;

import com.raghsonline.springboot.entity.User;

public interface UserService {

	public List<User> getAllUsers();
	
	public User getUser(Long id);
	
	public User saveUser(User user);
}
