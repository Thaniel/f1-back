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

import com.f1.Formula1.entities.Team;
import com.f1.Formula1.repositories.ITeamRepository;

public class TeamServiceTest {

	@Mock
	private ITeamRepository teamRepository;

	@InjectMocks
	private TeamService teamService;

	private Team teamTest1;
	private Team teamTest2;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		teamTest1 = new Team.Builder().id(1L).name("Red Bull").points(10).build();
		teamTest2 = new Team.Builder().id(2L).name("Mercedes").points(8).build();
	}

	/*
	 * getEntityId
	 */
	@Test
	void getEntityId_returnsCorrectId() {
		Long id = teamService.getEntityId(teamTest1);

		assertThat(id).isEqualTo(1L);
	}

	/*
	 * getTeamsSortedByPoints
	 */
	@Test
	void getTeamsSortedByPoints_returnTeamsInAscOrder() {
		when(teamRepository.findAll(Sort.by(Sort.Direction.ASC, "points")))
				.thenReturn(List.of(teamTest2, teamTest1));

		List<Team> result = teamService.getTeamsSortedByPoints(Sort.Direction.ASC);

		assertThat(result).isNotNull();
		assertThat(result).hasSize(2);
		assertThat(result.get(0).getId()).isEqualTo(2l);
		assertThat(result.get(1).getId()).isEqualTo(1l);

		verify(teamRepository).findAll(Sort.by(Sort.Direction.ASC, "points"));
	}

	@Test
	void getTeamsSortedByPoints_returnTeamsInDescOrder() {
		when(teamRepository.findAll(Sort.by(Sort.Direction.DESC, "points")))
				.thenReturn(List.of(teamTest1, teamTest2));

		List<Team> result = teamService.getTeamsSortedByPoints(Sort.Direction.DESC);

		assertThat(result).isNotNull();
		assertThat(result).hasSize(2);
		assertThat(result.get(0).getId()).isEqualTo(1l);
		assertThat(result.get(1).getId()).isEqualTo(2l);
		
		verify(teamRepository).findAll(Sort.by(Sort.Direction.DESC, "points"));
	}

	@Test
	void getTeamsSortedByPoints_notFound() {
		when(teamRepository.findAll(Sort.by(Sort.Direction.DESC, "points"))).thenReturn(List.of());

		List<Team> result = teamService.getTeamsSortedByPoints(Sort.Direction.DESC);

		assertThat(result).isEmpty();

		verify(teamRepository).findAll(Sort.by(Sort.Direction.DESC, "points"));

	}

	/*
	 * getTeamsByName
	 */
	@Test
	void getTeamsByName_foundTeams() {
		when(teamRepository.findByNameContainingIgnoreCase("Mercedes")).thenReturn(List.of(teamTest2));

		List<Team> result = teamService.getTeamsByName("Mercedes");

		assertThat(result).hasSize(1);
		assertThat(result.get(0).getName()).isEqualTo("Mercedes");

		verify(teamRepository).findByNameContainingIgnoreCase("Mercedes");
	}

	@Test
	void getTeamsByName_noTeamsFound() {
		when(teamRepository.findByNameContainingIgnoreCase("McLaren")).thenReturn(List.of());

		List<Team> result = teamService.getTeamsByName("McLaren");

		assertThat(result).isEmpty();

		verify(teamRepository).findByNameContainingIgnoreCase("McLaren");
	}
}
