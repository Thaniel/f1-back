package com.f1.Formula1.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.f1.Formula1.dto.DriverDTO;
import com.f1.Formula1.mapper.DriverMapper;
import com.f1.Formula1.model.Driver;
import com.f1.Formula1.service.DriverService;

@RestController
@RequestMapping("/drivers/")
public class DriverRestController {

	@Autowired
	private DriverService driverService;

	private String path = "/drivers/";

	/*
	 * Get Drivers
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<DriverDTO>> getAllDrivers() {
		List<Driver> drivers = driverService.getAll();
		
		if (drivers.isEmpty()) {
			return ResponseEntity.noContent().header("message", "No drivers found").build();
		}

		List<DriverDTO> driverDTOs = drivers.stream()
				.map(DriverMapper::toDTO)
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(driverDTOs);
	}

	/*
	 * Get Driver by Id
	 */
	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	// @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<DriverDTO> getDriverById(@PathVariable Long id) {
		Driver driver = driverService.getById(id);
		
		if (driver == null) {
			return ResponseEntity.noContent().header("message", "Driver not found with id: " + id).build();
		}
		
		DriverDTO driverDTO = DriverMapper.toDTO(driver);
		
		return ResponseEntity.ok(driverDTO);
	}

	/*
	 * Create Driver
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<DriverDTO> saveDriver(@RequestBody Driver driver) {
		try {
			Driver driverSaved = driverService.create(driver);
			URI uri = new URI(path.concat(driverSaved.getId().toString()));

			DriverDTO driverSavedDTO = DriverMapper.toDTO(driverSaved);
			
			return ResponseEntity.created(uri).body(driverSavedDTO);
			// return ResponseEntity.ok(driver);
		} catch (URISyntaxException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	/*
	 * Update Driver
	 */
	@PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DriverDTO> updateDriverById(@PathVariable long id, @RequestBody Driver driver) {
		Driver updatedDriver = driverService.update(driver);

		if (updatedDriver == null) {
			return ResponseEntity.notFound().header("message", "Driver not found with id: " + id).build();
		}
		
		DriverDTO updatedDriverDTO = DriverMapper.toDTO(updatedDriver);
		
		return ResponseEntity.ok(updatedDriverDTO);
	}

	/*
	 * Delete Driver
	 */
	@DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<DriverDTO> deleteDriver(@PathVariable Long id) {
		Driver driverDeleted = driverService.delete(id);

		if (driverDeleted == null) {
			return ResponseEntity.notFound().header("message", "Driver not found with id: " + id).build();
		}

		DriverDTO driverDeletedDTO = DriverMapper.toDTO(driverDeleted);
		
		return ResponseEntity.ok(driverDeletedDTO);
	}
	
	/*
	 * Get Drivers by Team Id
	 */
    @GetMapping(value = "/team/{teamId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DriverDTO>> getDriversByTeamId(@PathVariable Long teamId) {
        List<Driver> drivers = driverService.getDriversByTeamId(teamId);

        if (drivers.isEmpty()) {
            return ResponseEntity.noContent().header("message", "No drivers found for team id: " + teamId).build();
        }

        List<DriverDTO> driverDTOs = drivers.stream()
                                            .map(DriverMapper::toDTO)
                                            .collect(Collectors.toList());
                                            
        return ResponseEntity.ok(driverDTOs);
    }
    
	/*
	 * Get Drivers Ordered by Points
	 */
    @GetMapping(value = "/ordered", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DriverDTO>> getDriversOrderedByPoints() {
        List<Driver> drivers = driverService.getDriversOrderedByPoints();

        if (drivers.isEmpty()) {
            return ResponseEntity.noContent().header("message", "No drivers found").build();
        }

        List<DriverDTO> driverDTOs = drivers.stream()
                                            .map(DriverMapper::toDTO)
                                            .collect(Collectors.toList());
                                            
        return ResponseEntity.ok(driverDTOs);
    }
}
