package com.f1.Formula1.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.f1.Formula1.entities.Team;
import com.f1.Formula1.services.DriverService;
import com.f1.Formula1.services.TeamService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(TeamRestController.class)
public class TeamRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TeamService teamService;
	
	@MockBean
	private DriverService driverService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private Team teamTest1;
	private Team teamTest2;
	private Team teamTest1Update;

	@BeforeEach
	void setUp() {
		teamTest1 = new Team.Builder().id(1L).name("Red Bull").points(10).build();
		teamTest2 = new Team.Builder().id(2L).name("Mercedes").points(8).build();

		teamTest1Update = new Team.Builder().id(1L).name("RB").points(100).build();
	}

	/*
	 * GET /teams - Team list
	 */
	@Test
	void getAllTeams_returnsOk() throws Exception {
		when(teamService.getAll()).thenReturn(List.of(teamTest1, teamTest2));

		mockMvc.perform(get("/teams"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1L))
				.andExpect(jsonPath("$[0].name").value("Red Bull"))
				.andExpect(jsonPath("$[1].id").value(2L))
				.andExpect(jsonPath("$[1].name").value("Mercedes"));
		
		verify(teamService).getAll();
	}
	
	/*
	 * Not found
	 */
	@Test
	void getAllTeams_noContent() throws Exception {
		when(teamService.getAll()).thenReturn(List.of());

		mockMvc.perform(get("/teams"))
				.andExpect(status().isNoContent())
				.andExpect(header().string("message", "No teams found"));
		
		verify(teamService).getAll();
	}
	
	/*
	 * GET /teams/{id}
	 */
	@Test
	void getTeamById_found() throws Exception {
		when(teamService.getById(1L)).thenReturn(teamTest1);

		mockMvc.perform(get("/teams/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.name").value("Red Bull"));
		
		verify(teamService).getById(1L);
	}
	
	/*
	 * Not found
	 */
	@Test
	void getTeamById_notFound() throws Exception {
		when(teamService.getById(3L)).thenReturn(null);

		mockMvc.perform(get("/teams/3"))
				.andExpect(status().isNoContent())
				.andExpect(header().string("message", "Team not found with id: 3"));
		
		verify(teamService).getById(3L);
	}
	
	/*
	 * POST /teams
	 */
	@Test
	void saveTeam_createsTeam() throws Exception {
		when(teamService.create(any(Team.class))).thenReturn(teamTest1);

		mockMvc.perform(post("/teams").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(teamTest1)))
				.andExpect(status().isCreated())
				.andExpect(header().string("Location", "/teams/1"))
				.andExpect(jsonPath("$.name").value("Red Bull"));
		
		verify(teamService).create(any(Team.class));
	}
	
	/*
	 * PUT /teams/{id}
	 */
	@Test
	void updateTeam_ok() throws Exception {
		when(teamService.update(any(Team.class))).thenReturn(teamTest1Update);

		mockMvc.perform(put("/teams/1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(teamTest1Update)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("RB"))
				.andExpect(jsonPath("$.points").value("100"));
		
		verify(teamService).update(any(Team.class));
	}
	
	/*
	 * Not found
	 */
	@Test
	void updateTeam_notFound() throws Exception {
		when(teamService.update(any(Team.class))).thenReturn(null);

		mockMvc.perform(put("/teams/3").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(teamTest1)))
				.andExpect(status().isNotFound())
				.andExpect(header().string("message", "Team not found with id: 3"));
		
		verify(teamService).update(any(Team.class));
	}
	
	/*
	 * DELETE /teams/{id}
	 */
	@Test
	void deleteTeam_found() throws Exception {
		when(teamService.delete(1L)).thenReturn(teamTest1);

		mockMvc.perform(delete("/teams/1"))
                .andExpect(jsonPath("$.id").value(1L));
		
		verify(teamService).delete(1L);
	}
	
	/*
	 * Not found
	 */
	@Test
	void deleteTeam_notFound() throws Exception {
		when(teamService.delete(3L)).thenReturn(null);

		mockMvc.perform(delete("/teams/3"))
				.andExpect(status().isNotFound())
				.andExpect(header().string("message", "Team not found with id: 3"));
		
		verify(teamService).delete(3L);
	}
	
	/*
	 * GET /teams/sortedByPoints?order=asc
	 */
	@Test
	void getTeamsSortedByPointsAsc_found() throws Exception {
		when(teamService.getTeamsSortedByPoints(Sort.Direction.ASC)).thenReturn(List.of(teamTest2, teamTest1));

		mockMvc.perform(get("/teams/sortedByPoints").param("order", "asc"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(2L))
				.andExpect(jsonPath("$[0].points").value("8"))
				.andExpect(jsonPath("$[1].id").value(1L))
				.andExpect(jsonPath("$[1].points").value("10"));
		
		verify(teamService).getTeamsSortedByPoints(Sort.Direction.ASC);
	}
	
	/*
	 * GET /teams/sortedByPoints?order=desc
	 */
	@Test
	void getTeamsSortedByPointsDesc_found() throws Exception {
		when(teamService.getTeamsSortedByPoints(Sort.Direction.DESC)).thenReturn(List.of(teamTest1, teamTest2));

		mockMvc.perform(get("/teams/sortedByPoints").param("order", "desc"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1L))
				.andExpect(jsonPath("$[0].points").value("10"))
				.andExpect(jsonPath("$[1].id").value(2L))
				.andExpect(jsonPath("$[1].points").value("8"));
		
		verify(teamService).getTeamsSortedByPoints(Sort.Direction.DESC);
	}
	
	/*
	 * Not found
	 */
	@Test
	void getTeamsSortedByPoints_notFound() throws Exception {
		when(teamService.getTeamsSortedByPoints(Sort.Direction.ASC)).thenReturn(List.of());

		mockMvc.perform(get("/teams/sortedByPoints").param("order", "asc"))
				.andExpect(status().isNoContent())
				.andExpect(header().string("message", "No teams found"));
		
		verify(teamService).getTeamsSortedByPoints(Sort.Direction.ASC);
	}


	/*
	 * GET /teams/search?name=bull
	 */
	@Test
	void getTeamsByName_found() throws Exception {
		when(teamService.getTeamsByName("bull")).thenReturn(List.of(teamTest1));

		mockMvc.perform(get("/teams/search").param("name", "bull"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1L))
				.andExpect(jsonPath("$[0].name").value("Red Bull"));
		
		verify(teamService).getTeamsByName("bull");
	}
	
	/*
	 * Not found
	 */
	@Test
	void getTeamsByName_notFound() throws Exception {
		when(teamService.getTeamsByName("mclaren")).thenReturn(List.of());

		mockMvc.perform(get("/teams/search").param("name", "mclaren"))
				.andExpect(status().isNoContent())
				.andExpect(header().string("message", "No teams found for name: mclaren"));
		
		verify(teamService).getTeamsByName("mclaren");
	}
}
