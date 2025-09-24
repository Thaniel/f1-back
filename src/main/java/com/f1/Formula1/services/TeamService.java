package com.f1.Formula1.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.f1.Formula1.entities.Team;
import com.f1.Formula1.repositories.ITeamRepository;

@Service
public class TeamService extends AbstractCRUDService<Team, ITeamRepository> {

	public TeamService(ITeamRepository repository) {
		super(repository);
	}

	@Override
	protected Long getEntityId(Team team) {
		return team.getId();
	}
	
	public List<Team> getTeamsSortedByPoints(Sort.Direction direction){
		return repository.findAll(Sort.by(direction, "points"));
	}
	
	public List<Team> getTeamsByName(String name){
		return repository.findByNameContainingIgnoreCase(name);
	}
}