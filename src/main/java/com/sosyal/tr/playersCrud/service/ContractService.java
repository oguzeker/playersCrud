package com.sosyal.tr.playersCrud.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sosyal.tr.playersCrud.entity.Contract;
import com.sosyal.tr.playersCrud.entity.Player;
import com.sosyal.tr.playersCrud.entity.Team;
import com.sosyal.tr.playersCrud.exception.ResourceNotFoundException;
import com.sosyal.tr.playersCrud.repository.ContractRepository;

@Service
public class ContractService {

	private static final double COMMISSION_COEFFICIENT = 0.1;
	private static final int TRANSFER_PRICE_COEFFICIENT = 100000;

	@Autowired
    private ContractRepository contractDao;
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private TeamService teamService;

    public Contract addOrUpdateContract(Contract contract) {
    	Long playerId = contract.getPlayer().getPlayerId(); 
    	Long teamId = contract.getTeam().getTeamId();
    	
    	Player player = playerService.getPlayerById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException("Player", "id", playerId));
    	
    	Team team = teamService.getTeamById(teamId)
    			.orElseThrow(() -> new ResourceNotFoundException("Team", "id", teamId));
    	
    	Long transferPrice = calculateTransferPrice(
    			convertDateToLocalDate(player.getCareerStartDate()), 
    			convertDateToLocalDate(player.getBirthdate()));
    	contract.setTransferPrice(transferPrice);
    	
    	Long commission = calculateTeamCommission(transferPrice);
    	contract.setTeamCommission(commission);
    	
    	// Transfer price + team commission
    	contract.setTotalAmount(transferPrice + commission);
    	
    	contract.setPlayer(player);
    	contract.setTeam(team);
    	
        return contractDao.save(contract);
    }

    public List<Contract> getAllContracts() {
        return contractDao.findAll();
    }

    public Optional<Contract> getContractById(Long id) {
        return contractDao.findById(id);
    }

    public void deleteContract(Long id) {
        contractDao.deleteById(id);
    }

    // Experience amount in months * 100.000 / age of player
	private Long calculateTransferPrice(LocalDate careerStartDate, LocalDate birthdate) {
		return new Long(Period.between(careerStartDate, LocalDate.now()).toTotalMonths()
				* TRANSFER_PRICE_COEFFICIENT
				/ Period.between(birthdate, LocalDate.now()).getYears());
	}
	
	// Transfer price * 10%
	private long calculateTeamCommission(Long transferPrice) {
		return new Double(transferPrice * COMMISSION_COEFFICIENT).longValue();
	}
	
	private LocalDate convertDateToLocalDate(Date date) {
		Instant instant = Instant.ofEpochMilli(date.getTime());
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		return localDateTime.toLocalDate();
	}
    
}
