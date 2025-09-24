package com.f1.Formula1.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.f1.Formula1.entities.Driver;
import com.f1.Formula1.repositories.IDriverRepository;

@Service
public class DriverService extends AbstractCRUDService<Driver, IDriverRepository> {

	public DriverService(IDriverRepository repository) {
		super(repository);
	}

	@Override
	protected Long getEntityId(Driver driver) {
		return driver.getId();
	}

	public List<Driver> getDriversByTeamId(Long teamId) {
		return repository.findByTeamId(teamId);
	}

	public List<Driver> getDriversSortedByPoints(Sort.Direction direction) {
		return repository.findAll(Sort.by(direction, "points"));
	}

	public List<Driver> getDriversByName(String firstName) {
		return repository.findByFirstNameContainingIgnoreCase(firstName);
	}

	public List<Driver> getDriversByNameAndLastName(String firstName, String lastName) {
		return repository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(firstName, lastName);
	}
}