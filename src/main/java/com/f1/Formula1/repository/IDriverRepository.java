package com.f1.Formula1.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.f1.Formula1.model.Driver;

@Repository
public interface IDriverRepository extends JpaRepository<Driver, Long> {

	List<Driver> findByTeamId(Long teamId); // Spring Data JPA automatically interprets and makes the query
	
	List<Driver> findAll(Sort sort);
}