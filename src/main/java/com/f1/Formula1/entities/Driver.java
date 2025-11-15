package com.f1.Formula1.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "drivers")
public class Driver implements Serializable, ICopyable<Driver> {

	private static final long serialVersionUID = 6230553279333243193L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;
	private String firstName;
	private String lastName;
	private String country;
	private Date birthDate;
	private int points;
	private int titles;
	private String image;

	@ManyToOne
	@JoinColumn(name = "team_id") // Hibernate simplifies from @JoinColumn(name = "team_id", referencedColumnName = "id")
	private Team team;

	public Driver() {
		super();
	}

	public static class Builder {
		private Long id;
		private String firstName;
		private String lastName;
		private String country;
		private Date birthDate;
		private int points;
		private int titles;
		private String image;

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder country(String country) {
			this.country = country;
			return this;
		}

		public Builder birthDate(Date birthDate) {
			this.birthDate = birthDate;
			return this;
		}

		public Builder points(int points) {
			this.points = points;
			return this;
		}

		public Builder titles(int titles) {
			this.titles = titles;
			return this;
		}

		public Builder image(String image) {
			this.image = image;
			return this;
		}

		public Driver build() {
			Driver driver = new Driver();
			driver.id = this.id;
			driver.firstName = this.firstName;
			driver.lastName = this.lastName;
			driver.country = this.country;
			driver.birthDate = this.birthDate;
			driver.points = this.points;
			driver.titles = this.titles;
			driver.image = this.image;
			return driver;
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getTitles() {
		return titles;
	}

	public void setTitles(int titles) {
		this.titles = titles;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public void copyProperties(Driver driver) {
		this.id = driver.getId();
		this.firstName = driver.getFirstName();
		this.lastName = driver.getLastName();
		this.country = driver.getCountry();
		this.birthDate = driver.getBirthDate();
		this.points = driver.getPoints();
		this.titles = driver.getTitles();
		this.image = driver.getImage();
		this.team = driver.getTeam();
	}
}
