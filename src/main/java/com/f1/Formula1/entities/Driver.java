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

	public Driver(Long id, String firstName, String lastName, String country, Date birthDate, int points, int titles,
			String image, Team team) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.birthDate = birthDate;
		this.points = points;
		this.titles = titles;
		this.image = image;
		this.team = team;
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
