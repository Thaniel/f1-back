package com.f1.Formula1.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.f1.Formula1.entities.Race;
import com.f1.Formula1.repositories.IRaceRepository;

@Service
public class RaceService extends AbstractCRUDService<Race, IRaceRepository>{

	public RaceService(IRaceRepository repository) {
		super(repository);
	}

	@Override
	protected Long getEntityId(Race race) {
		return race.getId();
	}
	
	public List<Race> getRacesSortedByDate(Sort.Direction direction){
		return repository.findAll(Sort.by(direction, "raceDate"));
	}
	
	public List<Race> getRacesByCountry(String country){
		return repository.findByCountryContainingIgnoreCase(country);
	}
}