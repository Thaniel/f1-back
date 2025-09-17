package com.f1.Formula1.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.f1.Formula1.entities.User;

public interface IUserRepository extends JpaRepository<User, Long>{

	User findByUserName(String username);
}