package com.f1.Formula1.mapper;

import com.f1.Formula1.dto.DriverDTO;
import com.f1.Formula1.dto.DriverWithoutTeamDTO;
import com.f1.Formula1.model.Driver;

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