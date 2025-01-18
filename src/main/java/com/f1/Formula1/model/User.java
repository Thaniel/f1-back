package com.f1.Formula1.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity // JPA entity that represents a table in the database.
@Table(name = "users") // Name of the table in the database to map this class to. (Optional)
public class User implements Serializable, ICopyable<User>{

	private static final long serialVersionUID = -6147395907401254636L;
	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies the strategy for generating primary key values.
	@Column(name = "id", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "email", nullable = false, unique = true) // NOT NULL and UNIQUE
	private String email;

	@Column(name = "user_name", nullable = false, unique = true)
	private String userName;
	
	@Size(min = 2, max = 50)
	private String firstName;
	
	@Size(min = 2, max = 50)
	private String lastName;
	private Date birthDate;
	private boolean isAdmin;
	
	@Size(min = 2, max = 20)
	private String country;

	public User() {
		super();
	}

	public User(Long id, String firstName, String lastName, Date birthDate, boolean isAdmin, String email,
			String country, String userName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.isAdmin = isAdmin;
		this.email = email;
		this.country = country;
		this.userName = userName;
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public void copyProperties(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.userName = user.getUserName();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.birthDate = user.getBirthDate();
		this.isAdmin = user.isAdmin();
		this.country = user.getCountry();
	}
}
