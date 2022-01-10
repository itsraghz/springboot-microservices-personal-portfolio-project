package com.raghsonline.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raghsonline.springboot.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
