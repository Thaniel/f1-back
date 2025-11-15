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

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.f1.Formula1.entities.Race;
import com.f1.Formula1.services.RaceService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(RaceRestController.class)
public class RaceRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RaceService raceService;	
	
	@Autowired
	private ObjectMapper objectMapper; 

	private Race raceTest1;
	private Race raceTest2;

	@BeforeEach
	void setUp() {
		LocalDate localDate1 = LocalDate.of(2025, 7, 25);
		LocalDate localDate2 = LocalDate.of(2025, 2, 10);

		raceTest1 = new Race.Builder().id(1L).grandPrixName("Monaco GP").country("Monaco")
				.raceDate(Date.from(localDate1.atStartOfDay(ZoneId.systemDefault()).toInstant())).laps(78).build();

		raceTest2 = new Race.Builder().id(2L).grandPrixName("British GP").country("UK")
				.raceDate(Date.from(localDate2.atStartOfDay(ZoneId.systemDefault()).toInstant())).laps(52).build();
	}

	/*
	 * GET /races - Races list
	 */
	@Test
	void getAllRaces_returnsOk() throws Exception {
		when(raceService.getAll()).thenReturn(List.of(raceTest1, raceTest2));

		mockMvc.perform(get("/races"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1L))
				.andExpect(jsonPath("$[0].grandPrixName").value("Monaco GP"))
				.andExpect(jsonPath("$[1].id").value(2L))
				.andExpect(jsonPath("$[1].grandPrixName").value("British GP"));

		verify(raceService).getAll();
	}

	/*
	 * Not found
	 */
	@Test
	void getAllRaces_noContent() throws Exception {
		when(raceService.getAll()).thenReturn(List.of());

		mockMvc.perform(get("/races"))
				.andExpect(status().isNoContent())
				.andExpect(header().string("message", "No races found"));

		verify(raceService).getAll();
	}

	/*
	 * GET /races/{id}
	 */
	@Test
	void getRaceById_found() throws Exception {
		when(raceService.getById(1L)).thenReturn(raceTest1);

		mockMvc.perform(get("/races/1"))
				.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.grandPrixName").value("Monaco GP"));
		
        verify(raceService).getById(1L);
	}
	
	/*
	 * Not found
	 */
	@Test
	void getRaceById_notFound() throws Exception {
		when(raceService.getById(1L)).thenReturn(null);

		mockMvc.perform(get("/races/1"))
				.andExpect(status().isNoContent())
				.andExpect(header().string("message", "Race not found with id: 1"));
		
		verify(raceService).getById(1L);
	}
	
	/*
	 * POST /races
	 */
	@Test
	void saveRace_createsRace() throws Exception {
		when(raceService.create(any(Race.class))).thenReturn(raceTest1);

		mockMvc.perform(post("/races").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(raceTest1)))
				.andExpect(status().isCreated())
				.andExpect(header().string("Location", "/races/1"))
				.andExpect(jsonPath("$.grandPrixName").value("Monaco GP"));
		
		verify(raceService).create(any(Race.class));
	}

	/*
	 * PUT /races/{id}
	 */
	@Test
	void updateRace_ok() throws Exception {
		when(raceService.update(any(Race.class))).thenReturn(raceTest1);

		mockMvc.perform(put("/races/1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(raceTest1)))
				.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.grandPrixName").value("Monaco GP"));
		
		verify(raceService).update(any(Race.class));
	}
	
	/*
	 * Not found
	 */
	@Test
	void updateRace_notFound() throws Exception {
		when(raceService.update(any(Race.class))).thenReturn(null);

		mockMvc.perform(put("/races/1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(raceTest1)))
				.andExpect(status().isNotFound())
				.andExpect(header().string("message", "Race not found with id: 1"));
		
		verify(raceService).update(any(Race.class));
	}
	
	/*
	 * DELETE /races/{id}
	 */
	@Test
	void deleteUser_found() throws Exception {
		when(raceService.delete(1L)).thenReturn(raceTest1);

		mockMvc.perform(delete("/races/1"))
				.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.grandPrixName").value("Monaco GP"));
		
		verify(raceService).delete(1L);
	}

	/*
	 * Not found
	 */
	@Test
	void deleteUser_notFound() throws Exception {
		when(raceService.delete(1L)).thenReturn(null);

		mockMvc.perform(delete("/races/1"))
				.andExpect(status().isNotFound())
				.andExpect(header().string("message", "Race not found with id: 1"));
		
		verify(raceService).delete(1L);
	}
	
	/*
	 * GET /races/sorted?order=asc
	 */
	@Test
	void getRacesSortedByDateAsc_found() throws Exception {
		when(raceService.getRacesSortedByDate(Sort.Direction.ASC)).thenReturn(List.of(raceTest2, raceTest1));

		mockMvc.perform(get("/races/sorted").param("order", "asc"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(2L))
				.andExpect(jsonPath("$[0].grandPrixName").value("British GP"))
				.andExpect(jsonPath("$[1].id").value(1L))
				.andExpect(jsonPath("$[1].grandPrixName").value("Monaco GP"));
		
		verify(raceService).getRacesSortedByDate(Sort.Direction.ASC);
	}
	
	/*
	 * GET /races/sorted?order=desc
	 */
	@Test
	void getRacesSortedByDateDesc_found() throws Exception {
		when(raceService.getRacesSortedByDate(Sort.Direction.DESC)).thenReturn(List.of(raceTest1, raceTest2));

		mockMvc.perform(get("/races/sorted").param("order", "desc"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1L))
				.andExpect(jsonPath("$[0].grandPrixName").value("Monaco GP"))
				.andExpect(jsonPath("$[1].id").value(2L))
				.andExpect(jsonPath("$[1].grandPrixName").value("British GP"));
		
		verify(raceService).getRacesSortedByDate(Sort.Direction.DESC);
	}
	
	/*
	 * Not found
	 */
	@Test
	void getRacesSortedByDate_notFound() throws Exception {
		when(raceService.getRacesSortedByDate(Sort.Direction.ASC)).thenReturn(List.of());

		mockMvc.perform(get("/races/sorted").param("order", "asc"))
				.andExpect(status().isNoContent())
				.andExpect(header().string("message", "No races found"));
		
		verify(raceService).getRacesSortedByDate(Sort.Direction.ASC);
	}
	
	
	/*
	 * GET /races/search?country=Monaco
	 */
	@Test
	void getRacesByCountry_found() throws Exception {
		when(raceService.getRacesByCountry(raceTest1.getCountry())).thenReturn(List.of(raceTest1));

		mockMvc.perform(get("/races/search").param("country", raceTest1.getCountry()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1L))
				.andExpect(jsonPath("$[0].grandPrixName").value("Monaco GP"));
		
		verify(raceService).getRacesByCountry(raceTest1.getCountry());
	}

	/*
	 * Not found
	 */
	@Test
	void getRacesByCountry_notFound() throws Exception {
		when(raceService.getRacesByCountry("Spain")).thenReturn(List.of());

		mockMvc.perform(get("/races/search").param("country", "Spain"))
				.andExpect(status().isNoContent())
				.andExpect(header().string("message", "No races found for country: Spain"));
		
		verify(raceService).getRacesByCountry("Spain");
	}
}
