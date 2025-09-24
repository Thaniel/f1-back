package com.f1.Formula1.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.f1.Formula1.dtos.TeamDTO;
import com.f1.Formula1.entities.Team;
import com.f1.Formula1.mappers.TeamMapper;
import com.f1.Formula1.services.TeamService;

import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping("/teams")
public class TeamRestController {

	@Autowired
	private TeamService teamService;

	private String path = "/teams/";

	/*
	 * Get Teams
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed("teams.all")
	public ResponseEntity<List<TeamDTO>> getAllTeams() {
		List<Team> teams = teamService.getAll();

		List<TeamDTO> teamDTOs = teams.stream().map(TeamMapper::toDTO).collect(Collectors.toList());

		if (teams.isEmpty()) {
			return ResponseEntity.noContent().header("message", "No teams found").build();
		}

		return ResponseEntity.ok(teamDTOs);
	}

	/*
	 * Get Team by Id
	 */
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed("teams.by.id")
	public ResponseEntity<TeamDTO> getTeamById(@PathVariable Long id) {
		Team team = teamService.getById(id);

		TeamDTO teamDTO = TeamMapper.toDTO(team);

		if (team == null) {
			return ResponseEntity.noContent().header("message", "Team not found with id: " + id).build();
		}

		return ResponseEntity.ok(teamDTO);
	}

	/*
	 * Create Team
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TeamDTO> saveTeam(@RequestBody Team team) {
		try {
			Team teamSaved = teamService.create(team);
			URI uri = new URI(path.concat(teamSaved.getId().toString()));

			TeamDTO teamDTOSaved = TeamMapper.toDTO(teamSaved);

			return ResponseEntity.created(uri).body(teamDTOSaved);
			// return ResponseEntity.ok(team);
		} catch (URISyntaxException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	/*
	 * Update Team
	 */
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed("teams.update")
	public ResponseEntity<TeamDTO> updateTeamById(@PathVariable long id, @RequestBody Team team) {
		Team updatedTeam = teamService.update(team);

		if (updatedTeam == null) {
			return ResponseEntity.notFound().header("message", "Team not found with id: " + id).build();
		}

		TeamDTO updatedTeamDTO = TeamMapper.toDTO(updatedTeam);

		return ResponseEntity.ok(updatedTeamDTO);
	}

	/*
	 * Delete Team
	 */
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TeamDTO> deleteTeam(@PathVariable Long id) {
		Team teamDeleted = teamService.delete(id);

		if (teamDeleted == null) {
			return ResponseEntity.notFound().header("message", "Team not found with id: " + id).build();
		}

		TeamDTO teamDTODeleted = TeamMapper.toDTO(teamDeleted);

		return ResponseEntity.ok(teamDTODeleted);
	}

	/*
	 * Get Teams Ordered by Points
	 */
	@GetMapping(value = "/ordered", produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed("teams.ordered")
	public ResponseEntity<List<TeamDTO>> getTeamsOrderedByPoints() {
		List<Team> teams = teamService.getTeamsOrderedByPoints();

		if (teams.isEmpty()) {
			return ResponseEntity.noContent().header("message", "No teams found").build();
		}

		List<TeamDTO> teamDTOs = teams.stream().map(TeamMapper::toDTO).collect(Collectors.toList());

		return ResponseEntity.ok(teamDTOs);
	}

	/*
	 * Get Teams by Name
	 */
	@GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TeamDTO>> getTeamsByName(@RequestParam("name") String name) {
		List<Team> teams = teamService.getTeamsByName(name);

		if (teams.isEmpty()) {
			return ResponseEntity.noContent().header("message", "No teams found for name: " + name).build();
		}

		List<TeamDTO> teamsDTOs = teams.stream().map(TeamMapper::toDTO).collect(Collectors.toList());

		return ResponseEntity.ok(teamsDTOs);
	}
}
