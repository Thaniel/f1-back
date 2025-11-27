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

import com.f1.Formula1.entities.Driver;
import com.f1.Formula1.services.DriverService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(DriverRestController.class)
public class DriverRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DriverService driverService;

	@Autowired
	private ObjectMapper objectMapper;
	
	private Driver driverTest1;
	private Driver driverTest2;
	private Driver driverTest1Update;
	
	@BeforeEach
	void setUp() {
		driverTest1 = new Driver.Builder().id(1L).firstName("Max").lastName("Verstappen").points(100).build();
		driverTest2 = new Driver.Builder().id(2L).firstName("Fernando").lastName("Alonso").points(200).build();
		

		driverTest1Update = new Driver.Builder().id(1L).firstName("Maximo").lastName("Verstappen").points(1000).build();
	}
	
	/*
	 * GET /drivers - Driver list
	 */
	@Test
	void getAllDrivers_returnsOk() throws Exception {
		when(driverService.getAll()).thenReturn(List.of(driverTest1, driverTest2));

		mockMvc.perform(get("/drivers"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1L))
				.andExpect(jsonPath("$[0].firstName").value("Max"))
				.andExpect(jsonPath("$[1].id").value(2L))
				.andExpect(jsonPath("$[1].firstName").value("Fernando"));
		
		verify(driverService).getAll();
	}
	
	/*
	 * Not found
	 */
	@Test
	void getAllDrivers_noContent() throws Exception {
		when(driverService.getAll()).thenReturn(List.of());

		mockMvc.perform(get("/drivers"))
				.andExpect(status().isNoContent())
				.andExpect(header().string("message", "No drivers found"));
		
		verify(driverService).getAll();
	}
	
	/*
	 * GET /drivers/{id}
	 */
	@Test
	void getDriverById_found() throws Exception {
		when(driverService.getById(1L)).thenReturn(driverTest1);

		mockMvc.perform(get("/drivers/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.firstName").value("Max"));
		
		verify(driverService).getById(1L);
	}
	
	/*
	 * Not found
	 */
	@Test
	void getDriverById_notFound() throws Exception {
		when(driverService.getById(3L)).thenReturn(null);

		mockMvc.perform(get("/drivers/3"))
				.andExpect(status().isNoContent())
				.andExpect(header().string("message", "Driver not found with id: 3"));
		
		verify(driverService).getById(3L);
	}
	
	/*
	 * POST /drivers
	 */
	@Test
	void saveDriver_createsDriver() throws Exception {
		when(driverService.create(any(Driver.class))).thenReturn(driverTest1);

		mockMvc.perform(post("/drivers").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(driverTest1)))
				.andExpect(status().isCreated())
				.andExpect(header().string("Location", "/drivers/1"))
				.andExpect(jsonPath("$.firstName").value("Max"));
		
		verify(driverService).create(any(Driver.class));
	}
	
	/*
	 * PUT /drivers/{id}
	 */
	@Test
	void updateDriver_ok() throws Exception {
		when(driverService.update(any(Driver.class))).thenReturn(driverTest1Update);

		mockMvc.perform(put("/drivers/1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(driverTest1Update)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value("Maximo"))
				.andExpect(jsonPath("$.points").value("1000"));
		
		verify(driverService).update(any(Driver.class));
	}
	
	/*
	 * Not found
	 */
	@Test
	void updateDriver_notFound() throws Exception {
		when(driverService.update(any(Driver.class))).thenReturn(null);

		mockMvc.perform(put("/drivers/3").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(driverTest1)))
				.andExpect(status().isNotFound())
				.andExpect(header().string("message", "Driver not found with id: 3"));
		
		verify(driverService).update(any(Driver.class));
	}
	
	/*
	 * DELETE /drivers/{id}
	 */
	@Test
	void deleteDriver_found() throws Exception {
		when(driverService.delete(1L)).thenReturn(driverTest1);

		mockMvc.perform(delete("/drivers/1"))
				.andExpect(status().isOk())	
                .andExpect(jsonPath("$.id").value(1L));
		
		verify(driverService).delete(1L);
	}
	
	/*
	 * Not found
	 */
	@Test
	void deleteDriver_notFound() throws Exception {
		when(driverService.delete(3L)).thenReturn(null);

		mockMvc.perform(delete("/drivers/3"))
				.andExpect(status().isNotFound())
				.andExpect(header().string("message", "Driver not found with id: 3"));
		
		verify(driverService).delete(3L);
	}
	
	/*
	 * GET /drivers/sortedByPoints?order=asc
	 */
	@Test
	void getDriversSortedByPointsAsc_found() throws Exception {
		when(driverService.getDriversSortedByPoints(Sort.Direction.ASC)).thenReturn(List.of(driverTest1, driverTest2));

		mockMvc.perform(get("/drivers/sortedByPoints").param("order", "asc"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1L))
				.andExpect(jsonPath("$[0].points").value("100"))
				.andExpect(jsonPath("$[1].id").value(2L))
				.andExpect(jsonPath("$[1].points").value("200"));
		
		verify(driverService).getDriversSortedByPoints(Sort.Direction.ASC);
	}
	
	/*
	 * GET /drivers/sortedByPoints?order=desc
	 */
	@Test
	void getDriversSortedByPointsDesc_found() throws Exception {
		when(driverService.getDriversSortedByPoints(Sort.Direction.DESC)).thenReturn(List.of(driverTest2, driverTest1));

		mockMvc.perform(get("/drivers/sortedByPoints").param("order", "desc"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(2L))
				.andExpect(jsonPath("$[0].points").value("200"))
				.andExpect(jsonPath("$[1].id").value(1L))
				.andExpect(jsonPath("$[1].points").value("100"));
		
		verify(driverService).getDriversSortedByPoints(Sort.Direction.DESC);
	}
	
	/*
	 * Not found
	 */
	@Test
	void getDriversSortedByPoints_notFound() throws Exception {
		when(driverService.getDriversSortedByPoints(Sort.Direction.ASC)).thenReturn(List.of());

		mockMvc.perform(get("/drivers/sortedByPoints").param("order", "asc"))
				.andExpect(status().isNoContent())
				.andExpect(header().string("message", "No drivers found"));
		
		verify(driverService).getDriversSortedByPoints(Sort.Direction.ASC);
	}
	
	/*
	 * GET /drivers/search?firstName=max&lastName=verstapp
	 */
	@Test
	void getDriversByFirstnameAndLastName_found() throws Exception {
		when(driverService.getDriversByNameAndLastName("max", "verstapp")).thenReturn(List.of(driverTest1));

		mockMvc.perform(get("/drivers/search").param("firstName", "max").param("lastName", "verstapp"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1L))
				.andExpect(jsonPath("$[0].firstName").value("Max"))
				.andExpect(jsonPath("$[0].lastName").value("Verstappen"));
		
		verify(driverService).getDriversByNameAndLastName("max", "verstapp");
	}
	
	/*
	 * GET /drivers/search?firstName=max
	 */
	@Test
	void getDriversByFirstname_found() throws Exception {
		when(driverService.getDriversByName("max")).thenReturn(List.of(driverTest1));

		mockMvc.perform(get("/drivers/search").param("firstName", "max"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1L))
				.andExpect(jsonPath("$[0].firstName").value("Max"));
		
		verify(driverService).getDriversByName("max");
	}
	
	/*
	 * Not found
	 */
	@Test
	void getDriversByFirstnameAndLastName_notFound() throws Exception {
		when(driverService.getDriversByNameAndLastName("rob", "luccy")).thenReturn(List.of());

		mockMvc.perform(get("/drivers/search").param("firstName", "rob").param("lastName", "luccy"))
				.andExpect(status().isNoContent())
				.andExpect(header().string("message", "No drivers found for name: rob and surname: luccy"));
		
		verify(driverService).getDriversByNameAndLastName("rob", "luccy");
	}

	/*
	 * Not found
	 */
	@Test
	void getDriversByFirstname_notFound() throws Exception {
		when(driverService.getDriversByName("rob")).thenReturn(List.of());

		mockMvc.perform(get("/drivers/search").param("firstName", "rob"))
				.andExpect(status().isNoContent())
				.andExpect(header().string("message", "No drivers found for name: rob"));
		
		verify(driverService).getDriversByName("rob");
	}
}
