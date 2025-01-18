package com.f1.Formula1.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.f1.Formula1.model.Team;
import com.f1.Formula1.repository.ITeamRepository;

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