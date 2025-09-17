package com.f1.Formula1.dtos;

import java.util.Date;

import com.f1.Formula1.entities.Driver;

public class DriverWithoutTeamDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String country;
	private Date birthDate;
	private int points;
	private int titles;
	private String image;

	public DriverWithoutTeamDTO() {
		super();
	}
	
	public DriverWithoutTeamDTO(Driver driver) {
		super();
		this.id = driver.getId();
		this.firstName = driver.getFirstName();
		this.lastName = driver.getLastName();
		this.country = driver.getCountry();
		this.birthDate = driver.getBirthDate();
		this.points = driver.getPoints();
		this.titles = driver.getTitles();
		this.image = driver.getImage();
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
}
