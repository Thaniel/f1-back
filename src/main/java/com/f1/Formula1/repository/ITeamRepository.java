package com.f1.Formula1.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.f1.Formula1.model.Team;

@Repository
public interface ITeamRepository extends JpaRepository<Team, Long>{

	List<Team> findAll(Sort sort);
}
