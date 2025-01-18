package com.f1.Formula1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.f1.Formula1.model.User;

public interface IUserRepository extends JpaRepository<User, Long>{

	// TODO 
	// Add custom queris
}