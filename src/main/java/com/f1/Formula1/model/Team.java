package com.f1.Formula1.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "teams")
public class Team implements Serializable, ICopyable<Team> {

	private static final long serialVersionUID = 3930838590751219453L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
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
	
	@OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE)
	private List<Driver> drivers;
	
	public Team() {
		super();
	}

	public Team(Long id, String name, String fullName, String colorCode, String description, String teamPrincipal,
			String carImage, String logoImage, int titles, int points, List<Driver> drivers) {
		super();
		this.id = id;
		this.name = name;
		this.fullName = fullName;
		this.colorCode = colorCode;
		this.description = description;
		this.teamPrincipal = teamPrincipal;
		this.carImage = carImage;
		this.logoImage = logoImage;
		this.titles = titles;
		this.points = points;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Driver> getDrivers() {
		return drivers;
	}

	public void setDrivers(List<Driver> drivers) {
		this.drivers = drivers;
	}

	@Override
	public void copyProperties(Team team) {
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
		this.drivers = team.getDrivers();
	}
}
