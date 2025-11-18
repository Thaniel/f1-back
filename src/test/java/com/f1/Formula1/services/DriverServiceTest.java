package com.f1.Formula1.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import com.f1.Formula1.entities.Driver;
import com.f1.Formula1.repositories.IDriverRepository;

public class DriverServiceTest {

	@Mock
	private IDriverRepository driverRepository;

	@InjectMocks
	private DriverService driverService;

	private Driver driverTest1;
	private Driver driverTest2;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		driverTest1 = new Driver.Builder().id(1L).firstName("Max").lastName("Verstappen").points(100).build();
		driverTest2 = new Driver.Builder().id(2L).firstName("Fernando").lastName("Alonso").points(200).build();
	}

	/*
	 * getEntityId
	 */
	@Test
	void getEntityId_returnsCorrectId() {
		Long id = driverService.getEntityId(driverTest1);

		assertThat(id).isEqualTo(1L);
	}

	/*
	 * getDriversByTeamId
	 */
	@Test
	void getDriversByTeamId_found() {
		when(driverRepository.findByTeamId(1L)).thenReturn(List.of(driverTest1));

		List<Driver> result = driverService.getDriversByTeamId(1L);

		assertThat(result).isNotNull();
		assertThat(result).hasSize(1);
		assertThat(result.get(0).getId()).isEqualTo(1l);

		verify(driverRepository).findByTeamId(1L);
	}

	@Test
	void getDriversByTeamId_notFound() {
		when(driverRepository.findByTeamId(3L)).thenReturn(List.of());

		List<Driver> result = driverService.getDriversByTeamId(3L);

		assertThat(result).isEmpty();

		verify(driverRepository).findByTeamId(3L);
	}

	/*
	 * getDriversSortedByPoints
	 */
	@Test
	void getDriversSortedByPoints_returnDriverInAscOrder() {
		when(driverRepository.findAll(Sort.by(Sort.Direction.ASC, "points")))
				.thenReturn(List.of(driverTest1, driverTest2));

		List<Driver> result = driverService.getDriversSortedByPoints(Sort.Direction.ASC);

		assertThat(result).isNotNull();
		assertThat(result).hasSize(2);
		assertThat(result.get(0).getId()).isEqualTo(1l);
		assertThat(result.get(1).getId()).isEqualTo(2l);

		verify(driverRepository).findAll(Sort.by(Sort.Direction.ASC, "points"));
	}

	@Test
	void getDriversSortedByPoints_returnDriverInDescOrder() {
		when(driverRepository.findAll(Sort.by(Sort.Direction.DESC, "points")))
				.thenReturn(List.of(driverTest2, driverTest1));

		List<Driver> result = driverService.getDriversSortedByPoints(Sort.Direction.DESC);

		assertThat(result).isNotNull();
		assertThat(result).hasSize(2);
		assertThat(result.get(0).getId()).isEqualTo(2l);
		assertThat(result.get(1).getId()).isEqualTo(1l);

		verify(driverRepository).findAll(Sort.by(Sort.Direction.DESC, "points"));
	}

	@Test
	void getDriversSortedByPoints_notFound() {
		when(driverRepository.findAll(Sort.by(Sort.Direction.DESC, "points"))).thenReturn(List.of());

		List<Driver> result = driverService.getDriversSortedByPoints(Sort.Direction.DESC);

		assertThat(result).isEmpty();

		verify(driverRepository).findAll(Sort.by(Sort.Direction.DESC, "points"));
	}

	/*
	 * getDriversByName
	 */
	@Test
	void getDriversByName_foundDrivers() {
		when(driverRepository.findByFirstNameContainingIgnoreCase("Fer")).thenReturn(List.of(driverTest2));

		List<Driver> result = driverService.getDriversByName("Fer");

		assertThat(result).hasSize(1);
		assertThat(result.get(0).getFirstName()).isEqualTo("Fernando");

		verify(driverRepository).findByFirstNameContainingIgnoreCase("Fer");
	}

	@Test
	void getDriversByName_noDriversFound() {
		when(driverRepository.findByFirstNameContainingIgnoreCase("Leclerc")).thenReturn(List.of());

		List<Driver> result = driverService.getDriversByName("Leclerc");

		assertThat(result).isEmpty();

		verify(driverRepository).findByFirstNameContainingIgnoreCase("Leclerc");
	}

	/*
	 * getDriversByNameAndLastName
	 */
	@Test
	void getDriversByNameAndLastName_foundDrivers() {
		when(driverRepository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase("Fer", "Alon"))
				.thenReturn(List.of(driverTest2));

		List<Driver> result = driverService.getDriversByNameAndLastName("Fer", "Alon");

		assertThat(result).hasSize(1);
		assertThat(result.get(0).getFirstName()).isEqualTo("Fernando");
		assertThat(result.get(0).getLastName()).isEqualTo("Alonso");

		verify(driverRepository).findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase("Fer", "Alon");
	}

	@Test
	void getDriversByNameAndLastName_noDriversFound() {
		when(driverRepository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase("Leclerc", "Charles"))
				.thenReturn(List.of());

		List<Driver> result = driverService.getDriversByNameAndLastName("Leclerc", "Charles");

		assertThat(result).isEmpty();

		verify(driverRepository).findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase("Leclerc",
				"Charles");
	}
}
