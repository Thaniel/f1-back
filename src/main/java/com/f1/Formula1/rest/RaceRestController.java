package com.f1.Formula1.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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

import com.f1.Formula1.model.Race;
import com.f1.Formula1.service.RaceService;


@RestController
@RequestMapping("/races/")
public class RaceRestController {
	
	@Autowired
	private RaceService raceService;
	
	private String path = "/races/";

	/*
	 * Get Races
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<Race>> getAllRaces() {
		List<Race> races = raceService.getAll();

		if (races.isEmpty()) {
			return ResponseEntity.noContent().header("message", "No races found").build();
		}

		return ResponseEntity.ok(races);
	}
	
	/*
	 * Get Race by Id
	 */
	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Race> getRaceById(@PathVariable Long id) {
		Race race = raceService.getById(id);

		if (race == null) {
			return ResponseEntity.noContent().header("message", "Race not found with id: " + id).build();
		}

		return ResponseEntity.ok(race);
	}

	/*
	 * Create Race
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Race> saveRace(@RequestBody Race race) {
		try {
			Race raceSaved = raceService.create(race);
			URI uri = new URI(path.concat(raceSaved.getId().toString()));

			return ResponseEntity.created(uri).body(raceSaved);
		} catch (URISyntaxException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	/*
	 * Update Race
	 */
	@PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Race> updateUserById(@PathVariable long id, @RequestBody Race race) {
		Race updatedRace = raceService.update(race);

		if (updatedRace == null) {
			return ResponseEntity.notFound().header("message", "Race not found with id: " + id).build();
		}
		return ResponseEntity.ok(updatedRace);
	}
	
	/*
	 * Delete Race
	 */
	@DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Race> deleteUser(@PathVariable Long id) {
		Race raceDeleted = raceService.delete(id);

		if (raceDeleted == null) {
			return ResponseEntity.notFound().header("message", "Race not found with id: " + id).build();
		}

		return ResponseEntity.ok(raceDeleted);
	}
	
	/*
	 * Get Races Ordered by Date
	 */
    @GetMapping(value = "/ordered", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Race>> getRacesOrderedByDate() {
		List<Race> races = raceService.getRacesOrderedByDate();
		
        if (races.isEmpty()) {
            return ResponseEntity.noContent().header("message", "No races found").build();
        }
        
		return ResponseEntity.ok(races);
	}
	
}
