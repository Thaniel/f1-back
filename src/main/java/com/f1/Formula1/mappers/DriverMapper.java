package com.f1.Formula1.mappers;

import com.f1.Formula1.dtos.DriverDTO;
import com.f1.Formula1.dtos.DriverWithoutTeamDTO;
import com.f1.Formula1.entities.Driver;

public class DriverMapper {
	
	public static DriverDTO toDTO(Driver driver) {
		DriverDTO dto = new DriverDTO();
		
		dto.setId(driver.getId());
		dto.setFirstName(driver.getFirstName());
		dto.setLastName(driver.getLastName());
		dto.setCountry(driver.getCountry());
		dto.setBirthDate(driver.getBirthDate());
		dto.setPoints(driver.getPoints());
		dto.setTitles(driver.getTitles());
		dto.setImage(driver.getImage());
		
		dto.setTeam(TeamMapper.toDTOWithoutDrivers(driver.getTeam()));
		
		return dto;
	}

	public static DriverWithoutTeamDTO toDTOWithoutTeam(Driver driver) {
		DriverWithoutTeamDTO dto = new DriverWithoutTeamDTO();
		
		dto.setId(driver.getId());
		dto.setFirstName(driver.getFirstName());
		dto.setLastName(driver.getLastName());
		dto.setCountry(driver.getCountry());
		dto.setBirthDate(driver.getBirthDate());
		dto.setPoints(driver.getPoints());
		dto.setTitles(driver.getTitles());
		dto.setImage(driver.getImage());
		
		return dto;
	}
}