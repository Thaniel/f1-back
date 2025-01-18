package com.f1.Formula1.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.f1.Formula1.model.Race;

public interface IRaceRepository extends JpaRepository<Race, Long>{

	List<Race> findAll(Sort sort);
}