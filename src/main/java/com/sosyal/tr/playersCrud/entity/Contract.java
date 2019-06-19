package com.sosyal.tr.playersCrud.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


@Entity
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractId;
    
    @NotNull
    @ManyToOne
	@JoinColumn(name="playerId", nullable = false)
    private Player player;
    
    @NotNull
    @ManyToOne
	@JoinColumn(name="teamId", nullable = false)
    private Team team;
    
    private Long transferPrice;
    
    private Long teamCommission;
    
    private Long totalAmount;
    
    @NotNull
    private Long season;

	public Long getSeason() {
		return season;
	}

	public void setSeason(Long season) {
		this.season = season;
	}

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Long getTransferPrice() {
		return transferPrice;
	}

	public void setTransferPrice(Long transferPrice) {
		this.transferPrice = transferPrice;
	}

	public Long getTeamCommission() {
		return teamCommission;
	}

	public void setTeamCommission(Long teamCommission) {
		this.teamCommission = teamCommission;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}
    
}
