package com.raghsonline.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raghsonline.springboot.entity.User;
import com.raghsonline.springboot.repository.UserRepository;
import com.raghsonline.springboot.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		log.info("UserRepository - getAllUsers() invoked!");
		return userRepository.findAll();
	}

	@Override
	public User getUser(Long id) {
		log.info("UserRepository - getUser(id) invoked with id=" + id);
		return userRepository.findById(id).get();
	}

	@Override
	public User saveUser(User user) {
		log.info("UserRepository - saveUser(user) invoked with user=" + user);
		return userRepository.save(user);
	}

}
