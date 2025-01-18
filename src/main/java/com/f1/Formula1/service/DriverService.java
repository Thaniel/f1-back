package com.f1.Formula1.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.f1.Formula1.model.Driver;
import com.f1.Formula1.repository.IDriverRepository;

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
    
    public List<Driver> getDriversOrderedByPoints() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "points"));
    }
}