package com.f1.Formula1.entities;

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

	public static class Builder {
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

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder fullName(String fullName) {
			this.fullName = fullName;
			return this;
		}

		public Builder colorCode(String colorCode) {
			this.colorCode = colorCode;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder teamPrincipal(String teamPrincipal) {
			this.teamPrincipal = teamPrincipal;
			return this;
		}

		public Builder carImage(String carImage) {
			this.carImage = carImage;
			return this;
		}

		public Builder logoImage(String logoImage) {
			this.logoImage = logoImage;
			return this;
		}

		public Builder titles(int titles) {
			this.titles = titles;
			return this;
		}

		public Builder points(int points) {
			this.points = points;
			return this;
		}

		public Team build() {
			Team team = new Team();
			team.id = this.id;
			team.name = this.name;
			team.fullName = this.fullName;
			team.colorCode = this.colorCode;
			team.description = this.description;
			team.teamPrincipal = this.teamPrincipal;
			team.carImage = this.carImage;
			team.logoImage = this.logoImage;
			team.titles = this.titles;
			team.points = this.points;
			return team;
		}
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
