package com.sosyal.tr.playersCrud.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerId;
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "player")
    private List<Contract> contracts;
    
    @NotNull
    private Date birthdate;
    
    @NotNull
    private Date careerStartDate;
    
    private String name;

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public List<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Date getCareerStartDate() {
		return careerStartDate;
	}

	public void setCareerStartDate(Date careerStartDate) {
		this.careerStartDate = careerStartDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
