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
	
	public List<Team> getTeamsOrderedByPoints(){
		return repository.findAll(Sort.by(Sort.Direction.DESC, "points"));
	}
}