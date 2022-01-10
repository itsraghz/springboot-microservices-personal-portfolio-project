package com.raghsonline.springboot.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.raghsonline.springboot.consumer.IProfileConsumerFeign;
import com.raghsonline.springboot.model.User;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/ppp/")
@Slf4j
public class ParentServiceController {
	
	@Autowired
	private IProfileConsumerFeign profileConsumer;
	
	@GetMapping("/")
	public String homePage(Model model) {
		log.info("ParentServiceController - homePage() called");
		model.addAttribute("user", "PPP");
		model.addAttribute("count", 20);
		return "index";
	}
	
	@GetMapping("/profile/sample")
	public String getAllUsersSample(Model model) {
		log.info("ParentServiceController - getAllUsersSample() invoked!");
		model.addAttribute("usersList", getSampleUsers());
		return "profile/list";
	}
	
	@GetMapping("/profile")
	public String getAllUsers(Model model) {
		log.info("ParentServiceController - getAllUsers() invoked!");
		List<User> usersList = profileConsumer.getAllUsers().getBody();
		model.addAttribute("usersList", usersList);
		return "profile/list";
	}
	
	@GetMapping("/profile/{id}")
	public String getUser(Model model, @PathVariable Long id) {
		log.info("ParentServiceController - getUser() invoked with id="+id);
		User user = profileConsumer.getUser(id).getBody();
		model.addAttribute("user", user);
		return "profile/view";
	}
	
	@GetMapping("/profile/add")
	public String addUser(Model model) {
		log.info("ParentServiceController - addUser() invoked");
		model.addAttribute("user", new User());
		return "profile/add";
	}
	
	@PostMapping("/profile")
	public String saveUser(Model model, @ModelAttribute User user) {
		log.info("ParentServiceController - saveUser() invoked, user="+user);
		user = profileConsumer.saveUser(user).getBody();
		model.addAttribute("user", user);
		return "redirect:/ppp/profile";
	}
	
	@GetMapping("/profile/edit/{id}")
	public String editUser(Model model, @PathVariable Long id) {
		log.info("ParentServiceController - editUser() invoked with id="+id);
		User user = profileConsumer.getUser(id).getBody();
		model.addAttribute("user", user);
		return "profile/edit";
	}
	
	@PostMapping("/profile/update")
	public String updateUser(Model model, @ModelAttribute User user) {
		log.info("ParentServiceController - updateUser() invoked, user="+user);
		user = profileConsumer.saveUser(user).getBody();
		model.addAttribute("user", user);
		return "redirect:/ppp/profile/" + user.getId();
	}
	
	private List<User> getSampleUsers() {
		return Arrays.asList(
				new User(4L, "Raja", "Ram", "raghavan@example.com", "4567890123", "raja", "raja@456"),
				new User(5L, "Sabarish", "Mahalingam", "sabarish@example.com", "5678901234", "sabarish", "sabarish@567"),
				new User(6L, "Shilpa", "L", "shilpa@example.com", "6789012345", "shilpa", "shilpa@678")
				);
	}
}
