package com.f1.Formula1.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.f1.Formula1.dtos.DriverWithoutTeamDTO;
import com.f1.Formula1.dtos.TeamDTO;
import com.f1.Formula1.dtos.TeamWithoutDriversDTO;
import com.f1.Formula1.entities.Team;

public class TeamMapper {

	public static TeamDTO toDTO(Team team) {
		TeamDTO dto = null;

		if (team != null) {
			List<DriverWithoutTeamDTO> driverWithoutTeamDTOs = team.getDrivers().stream().map(DriverMapper::toDTOWithoutTeam)
					.collect(Collectors.toList());
			dto = new TeamDTO(team, driverWithoutTeamDTOs);
		}
		
		return dto;
	}

	public static TeamWithoutDriversDTO toDTOWithoutDrivers(Team team) {
		TeamWithoutDriversDTO dto = null;

		if (team != null) {
			dto = new TeamWithoutDriversDTO(team); 
		}
		
		return dto;
	}
}
