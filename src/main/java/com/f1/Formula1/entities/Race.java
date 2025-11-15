package com.f1.Formula1.entities;

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
public class Race implements Serializable, ICopyable<Race> {

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

	public static class Builder {
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

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder grandPrixName(String grandPrixName) {
			this.grandPrixName = grandPrixName;
			return this;
		}

		public Builder firstAppearance(int firstAppearance) {
			this.firstAppearance = firstAppearance;
			return this;
		}

		public Builder raceDistance(Double raceDistance) {
			this.raceDistance = raceDistance;
			return this;
		}

		public Builder circuitName(String circuitName) {
			this.circuitName = circuitName;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder country(String country) {
			this.country = country;
			return this;
		}

		public Builder image(String image) {
			this.image = image;
			return this;
		}

		public Builder record(String record) {
			this.record = record;
			return this;
		}

		public Builder laps(int laps) {
			this.laps = laps;
			return this;
		}

		public Builder circuitLength(Double circuitLength) {
			this.circuitLength = circuitLength;
			return this;
		}

		public Builder raceDate(Date raceDate) {
			this.raceDate = raceDate;
			return this;
		}

		public Builder qualifyingDate(Date qualifyingDate) {
			this.qualifyingDate = qualifyingDate;
			return this;
		}

		public Builder firstPracticeDate(Date firstPracticeDate) {
			this.firstPracticeDate = firstPracticeDate;
			return this;
		}

		public Builder secondPracticeDate(Date secondPracticeDate) {
			this.secondPracticeDate = secondPracticeDate;
			return this;
		}

		public Builder thirdPracticeDate(Date thirdPracticeDate) {
			this.thirdPracticeDate = thirdPracticeDate;
			return this;
		}

		public Race build() {
			Race race = new Race();
			race.id = this.id;
			race.grandPrixName = this.grandPrixName;
			race.firstAppearance = this.firstAppearance;
			race.raceDistance = this.raceDistance;
			race.circuitName = this.circuitName;
			race.description = this.description;
			race.country = this.country;
			race.image = this.image;
			race.record = this.record;
			race.laps = this.laps;
			race.circuitLength = this.circuitLength;
			race.raceDate = this.raceDate;
			race.qualifyingDate = this.qualifyingDate;
			race.firstPracticeDate = this.firstPracticeDate;
			race.secondPracticeDate = this.secondPracticeDate;
			race.thirdPracticeDate = this.thirdPracticeDate;
			return race;
		}

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
