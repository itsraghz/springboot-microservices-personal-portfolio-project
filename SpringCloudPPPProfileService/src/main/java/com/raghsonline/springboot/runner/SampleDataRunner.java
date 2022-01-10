package com.raghsonline.springboot.runner;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.raghsonline.springboot.entity.User;
import com.raghsonline.springboot.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SampleDataRunner implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		log.info("SampleDataRunner - run() invoked!");
		checkAndInsertSampleRows();
		log.info("SampleDataRunner, run() method completed!");
		
	}

	private void checkAndInsertSampleRows() {
		if(!isDatabaseEmpty()) {
			log.info("Database has some entries. NOT EMPTY!");
			return;
		}
		log.info("Database seems to be empty! Inserting some sample rows!");
		List<User> usersList = getSampleUsers();
		log.info("Total # of users to be inserted : " + usersList.stream().count());
		for(User user: usersList) {
			log.info("Inserting User : " + user);
			user = userRepository.save(user);
			log.info("Inserted User : " + user);
		}
	}
	
	private boolean isDatabaseEmpty() {
		return userRepository.findAll().size()==0;
	}
	
	public List<User> getSampleUsers() {
		return Arrays.asList(
				new User(null, "Raghavan", "Muthu", "raghavan@example.com", "1234567890", "raghs", "raghs@123"),
				new User(null, "Kannan", "Muthu", "kannan@example.com", "2345678901", "kanna", "kanna@123"),
				new User(null, "Karpagam", "Muthu", "karpagam@example.com", "3456789012", "karps", "karps@123")
				);
	}

}
