package com.f1.Formula1.mappers;

import com.f1.Formula1.dtos.DriverDTO;
import com.f1.Formula1.dtos.DriverWithoutTeamDTO;
import com.f1.Formula1.dtos.TeamWithoutDriversDTO;
import com.f1.Formula1.entities.Driver;

public class DriverMapper {
	
	public static DriverDTO toDTO(Driver driver) {
		DriverDTO dto = null;
		
		if (driver != null) {
			TeamWithoutDriversDTO teamWithoutDriversDTO = TeamMapper.toDTOWithoutDrivers(driver.getTeam());
			dto = new DriverDTO(driver, teamWithoutDriversDTO);
		}
		
		
		return dto;
	}

	public static DriverWithoutTeamDTO toDTOWithoutTeam(Driver driver) {
		DriverWithoutTeamDTO dto = null;
		
		if (driver != null) {
			dto = new DriverWithoutTeamDTO(driver);
		}
		
		return dto;
	}
	
}