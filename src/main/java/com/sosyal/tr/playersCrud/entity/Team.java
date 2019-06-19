package com.sosyal.tr.playersCrud.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Team {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "team")
    private List<Contract> contracts;
    
    private String currency;

    private String name;
    
	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public List<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
}
