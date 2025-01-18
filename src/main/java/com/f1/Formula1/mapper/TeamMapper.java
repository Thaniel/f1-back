package com.f1.Formula1.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.f1.Formula1.dto.DriverWithoutTeamDTO;
import com.f1.Formula1.dto.TeamDTO;
import com.f1.Formula1.dto.TeamWithoutDriversDTO;
import com.f1.Formula1.model.Team;

public class TeamMapper {
	
	public static TeamDTO toDTO(Team team) {
        TeamDTO dto = new TeamDTO();
        
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

        List<DriverWithoutTeamDTO> drivers = team.getDrivers().stream()
                                      .map(DriverMapper::toDTOWithoutTeam)
                                      .collect(Collectors.toList());
        dto.setDrivers(drivers);
        return dto;
    }
	
	public static TeamWithoutDriversDTO toDTOWithoutDrivers(Team team) {
		TeamWithoutDriversDTO dto = new TeamWithoutDriversDTO();
        
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

        return dto;
    }
}
