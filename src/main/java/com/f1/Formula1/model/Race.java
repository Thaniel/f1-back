package com.f1.Formula1.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "races") 
public class Race implements Serializable, ICopyable<Race>{

	private static final long serialVersionUID = 4319149267041274010L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;
	
	private String grandPrixName;
	private int firstAppearance;
	private Double raceDistance;
	private String circuitName;
	private String description;
	private String country;
	private String image;
	private String record;
	private int laps;
	private Double circuitLength;
	private Date raceDate;
	private Date qualifyingDate;
	private Date firstPracticeDate;
	private Date secondPracticeDate;
	private Date thirdPracticeDate;
	
	public Race() {
		super();
	}

	public Race(Long id, String grandPrixName, int firstAppearance, Double raceDistance, String circuitName,
			String description, String country, String image, int laps, Double circuitLength, Date raceDate,
			Date qualifyingDate, Date firstPracticeDate, Date secondPracticeDate, Date thirdPracticeDate,
			String record) {
		super();
		this.id = id;
		this.grandPrixName = grandPrixName;
		this.firstAppearance = firstAppearance;
		this.raceDistance = raceDistance;
		this.circuitName = circuitName;
		this.description = description;
		this.country = country;
		this.image = image;
		this.laps = laps;
		this.circuitLength = circuitLength;
		this.raceDate = raceDate;
		this.qualifyingDate = qualifyingDate;
		this.firstPracticeDate = firstPracticeDate;
		this.secondPracticeDate = secondPracticeDate;
		this.thirdPracticeDate = thirdPracticeDate;
		this.record = record;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGrandPrixName() {
		return grandPrixName;
	}

	public void setGrandPrixName(String grandPrixName) {
		this.grandPrixName = grandPrixName;
	}

	public int getFirstAppearance() {
		return firstAppearance;
	}

	public void setFirstAppearance(int firstAppearance) {
		this.firstAppearance = firstAppearance;
	}

	public Double getRaceDistance() {
		return raceDistance;
	}

	public void setRaceDistance(Double raceDistance) {
		this.raceDistance = raceDistance;
	}

	public String getCircuitName() {
		return circuitName;
	}

	public void setCircuitName(String circuitName) {
		this.circuitName = circuitName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getLaps() {
		return laps;
	}

	public void setLaps(int laps) {
		this.laps = laps;
	}

	public Double getCircuitLength() {
		return circuitLength;
	}

	public void setCircuitLength(Double circuitLength) {
		this.circuitLength = circuitLength;
	}

	public Date getRaceDate() {
		return raceDate;
	}

	public void setRaceDate(Date raceDate) {
		this.raceDate = raceDate;
	}

	public Date getQualifyingDate() {
		return qualifyingDate;
	}

	public void setQualifyingDate(Date qualifyingDate) {
		this.qualifyingDate = qualifyingDate;
	}

	public Date getFirstPracticeDate() {
		return firstPracticeDate;
	}

	public void setFirstPracticeDate(Date firstPracticeDate) {
		this.firstPracticeDate = firstPracticeDate;
	}

	public Date getSecondPracticeDate() {
		return secondPracticeDate;
	}

	public void setSecondPracticeDate(Date secondPracticeDate) {
		this.secondPracticeDate = secondPracticeDate;
	}

	public Date getThirdPracticeDate() {
		return thirdPracticeDate;
	}

	public void setThirdPracticeDate(Date thirdPracticeDate) {
		this.thirdPracticeDate = thirdPracticeDate;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public void copyProperties(Race race) {
		this.id = race.getId();
		this.grandPrixName = race.getGrandPrixName();
		this.firstAppearance = race.getFirstAppearance();
		this.raceDistance = race.getRaceDistance();
		this.circuitName = race.getCircuitName();
		this.description = race.getDescription();
		this.country = race.getCountry();
		this.image = race.getImage();
		this.laps = race.getLaps();
		this.circuitLength = race.getCircuitLength();
		this.raceDate = race.getRaceDate();
		this.qualifyingDate = race.getQualifyingDate();
		this.firstPracticeDate = race.getFirstPracticeDate();
		this.secondPracticeDate = race.getSecondPracticeDate();
		this.thirdPracticeDate = race.getThirdPracticeDate();
		this.record = race.getRecord();
	}
}
