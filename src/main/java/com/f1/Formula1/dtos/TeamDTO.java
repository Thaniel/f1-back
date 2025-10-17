package com.f1.Formula1.dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.f1.Formula1.entities.Team;
import com.f1.Formula1.mappers.DriverMapper;

public class TeamDTO {
	
	private Long id;
	private String name;
	private String fullName;
	private String colorCode;
	private String description;
	private String teamPrincipal;
	private String carImage;
	private String logoImage;
	private int titles;
	private int points;
	private List<DriverWithoutTeamDTO> drivers;

	public TeamDTO() {
		super();
	}
	
	public TeamDTO(Team team, List<DriverWithoutTeamDTO> drivers) {
		super();
		this.id = team.getId();
		this.name = team.getName();
		this.fullName = team.getFullName();
		this.colorCode = team.getColorCode();
		this.description = team.getDescription();
		this.teamPrincipal = team.getTeamPrincipal();
		this.carImage = team.getCarImage();
		this.logoImage = team.getLogoImage();
		this.titles = team.getTitles();
		this.points = team.getPoints();
		this.drivers = drivers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTeamPrincipal() {
		return teamPrincipal;
	}

	public void setTeamPrincipal(String teamPrincipal) {
		this.teamPrincipal = teamPrincipal;
	}

	public String getCarImage() {
		return carImage;
	}

	public void setCarImage(String carImage) {
		this.carImage = carImage;
	}

	public String getLogoImage() {
		return logoImage;
	}

	public void setLogoImage(String logoImage) {
		this.logoImage = logoImage;
	}

	public int getTitles() {
		return titles;
	}

	public void setTitles(int titles) {
		this.titles = titles;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public List<DriverWithoutTeamDTO> getDrivers() {
		return drivers;
	}

	public void setDrivers(List<DriverWithoutTeamDTO> drivers) {
		this.drivers = drivers;
	}
}
