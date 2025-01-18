package com.f1.Formula1.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.f1.Formula1.model.Race;
import com.f1.Formula1.repository.IRaceRepository;

@Service
public class RaceService extends AbstractCRUDService<Race, IRaceRepository>{

	public RaceService(IRaceRepository repository) {
		super(repository);
	}

	@Override
	protected Long getEntityId(Race race) {
		return race.getId();
	}
	
	public List<Race> getRacesOrderedByDate(){
		return repository.findAll(Sort.by(Sort.Direction.ASC, "raceDate"));
	}
}