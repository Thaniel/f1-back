package com.f1.Formula1.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import com.f1.Formula1.entities.Race;
import com.f1.Formula1.repositories.IRaceRepository;

public class RaceServiceTest {

	@Mock
	private IRaceRepository raceRepository;

	@InjectMocks
	private RaceService raceService;

	private Race raceTest1;
	private Race raceTest2;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		Date date1 = toDate(LocalDate.of(2025, 7, 25));
		Date date2 = toDate(LocalDate.of(2025, 2, 10));

		raceTest1 = new Race.Builder().id(1L).grandPrixName("Monaco GP").country("Monaco").raceDate(date1).laps(78)
				.build();

		raceTest2 = new Race.Builder().id(2L).grandPrixName("British GP").country("UK").raceDate(date2).laps(52)
				.build();
	}

	private Date toDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	/*
	 * getEntityId
	 */
	@Test
	void getEntityId_returnsCorrectId() {
		Long id = raceService.getEntityId(raceTest1);

		assertThat(id).isEqualTo(1L);
	}

	/*
	 * getRacesSortedByDate
	 */
	@Test
	void getRacesSortedByDate_returnsRacesInAscOrder() {
		when(raceRepository.findAll(Sort.by(Sort.Direction.ASC, "raceDate"))).thenReturn(List.of(raceTest2, raceTest1));

		List<Race> result = raceService.getRacesSortedByDate(Sort.Direction.ASC);

		assertThat(result).isNotNull();
		assertThat(result).hasSize(2);
		assertThat(result.get(0).getId()).isEqualTo(2L);
		assertThat(result.get(0).getGrandPrixName()).isEqualTo("British GP");
		assertThat(result.get(1).getId()).isEqualTo(1L);
		assertThat(result.get(1).getGrandPrixName()).isEqualTo("Monaco GP");

		verify(raceRepository).findAll(Sort.by(Sort.Direction.ASC, "raceDate"));

	}

	@Test
	void getRacesSortedByDate_returnsRacesInDescOrder() {
		when(raceRepository.findAll(Sort.by(Sort.Direction.DESC, "raceDate")))
				.thenReturn(List.of(raceTest1, raceTest2));

		List<Race> result = raceService.getRacesSortedByDate(Sort.Direction.DESC);

		assertThat(result).isNotNull();
		assertThat(result).hasSize(2);
		assertThat(result.get(0).getId()).isEqualTo(1L);
		assertThat(result.get(0).getGrandPrixName()).isEqualTo("Monaco GP");
		assertThat(result.get(1).getId()).isEqualTo(2L);
		assertThat(result.get(1).getGrandPrixName()).isEqualTo("British GP");

		verify(raceRepository).findAll(Sort.by(Sort.Direction.DESC, "raceDate"));

	}

	@Test
	void getRacesSortedByDate_notFound() {
		when(raceRepository.findAll(Sort.by(Sort.Direction.DESC, "raceDate"))).thenReturn(List.of());

		List<Race> result = raceService.getRacesSortedByDate(Sort.Direction.DESC);

		assertThat(result).isEmpty();

		verify(raceRepository).findAll(Sort.by(Sort.Direction.DESC, "raceDate"));

	}

	/*
	 * getRacesByCountry
	 */
	@Test
	void getRacesByCountry_foundRaces() {
		when(raceRepository.findByCountryContainingIgnoreCase("Monaco")).thenReturn(List.of(raceTest1));

		List<Race> result = raceService.getRacesByCountry("Monaco");

		assertThat(result).hasSize(1);
		assertThat(result.get(0).getCountry()).isEqualTo("Monaco");

		verify(raceRepository).findByCountryContainingIgnoreCase("Monaco");
	}

	@Test
	void getRacesByCountry_noRacesFound() {
		when(raceRepository.findByCountryContainingIgnoreCase("Spain")).thenReturn(List.of());

		List<Race> result = raceService.getRacesByCountry("Spain");

		assertThat(result).isEmpty();

		verify(raceRepository).findByCountryContainingIgnoreCase("Spain");
	}
}
