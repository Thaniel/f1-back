package com.f1.Formula1.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.f1.Formula1.entities.Team;

@Repository
public interface ITeamRepository extends JpaRepository<Team, Long>{

	List<Team> findAll(Sort sort);
}
