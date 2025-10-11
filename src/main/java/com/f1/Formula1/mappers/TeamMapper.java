package com.f1.Formula1.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.f1.Formula1.dtos.DriverWithoutTeamDTO;
import com.f1.Formula1.dtos.TeamDTO;
import com.f1.Formula1.dtos.TeamWithoutDriversDTO;
import com.f1.Formula1.entities.Team;

public class TeamMapper {

	public static TeamDTO toDTO(Team team) {
		TeamDTO dto = new TeamDTO();

		if (team != null) {
			dto.setId(team.getId());
			dto.setName(team.getName());
			dto.setFullName(team.getFullName());
			dto.setColorCode(team.getColorCode());
			dto.setDescription(team.getDescription());
			dto.setTeamPrincipal(team.getTeamPrincipal());
			dto.setCarImage(team.getCarImage());
			dto.setLogoImage(team.getLogoImage());
			dto.setTitles(team.getTitles());
			dto.setPoints(team.getPoints());

			List<DriverWithoutTeamDTO> drivers = team.getDrivers().stream().map(DriverMapper::toDTOWithoutTeam)
					.collect(Collectors.toList());
			dto.setDrivers(drivers);
		}
		return dto;
	}

	public static TeamWithoutDriversDTO toDTOWithoutDrivers(Team team) {
		TeamWithoutDriversDTO dto = new TeamWithoutDriversDTO();

		if (team != null) {
			dto.setId(team.getId());
			dto.setName(team.getName());
			dto.setFullName(team.getFullName());
			dto.setColorCode(team.getColorCode());
			dto.setDescription(team.getDescription());
			dto.setTeamPrincipal(team.getTeamPrincipal());
			dto.setCarImage(team.getCarImage());
			dto.setLogoImage(team.getLogoImage());
			dto.setTitles(team.getTitles());
			dto.setPoints(team.getPoints());
		}
		
		return dto;
	}
}
