package com.sosyal.tr.playersCrud.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sosyal.tr.playersCrud.entity.Player;
import com.sosyal.tr.playersCrud.entity.Team;
import com.sosyal.tr.playersCrud.exception.ResourceNotFoundException;
import com.sosyal.tr.playersCrud.service.PlayerService;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private static final String TEAMS_OF_PLAYER = "TEAMS OF PLAYER";
	private static final String ID = "id";
	private static final String PLAYER = "Player";
	
	@Autowired
    private PlayerService playerService;

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @PostMapping
    public Player createPlayer(@Valid @RequestBody Player player) {
    	player.setPlayerId(null);
        return playerService.addOrUpdatePlayer(player);
    }

    @GetMapping("{id}")
    public Player getPlayerById(@PathVariable(value = ID) Long playerId) {
        return playerService.getPlayerById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException(PLAYER, ID, playerId));
    }

    @PutMapping("{id}")
    public Player updatePlayer(@PathVariable(value = ID) Long playerId,
                                           @Valid @RequestBody Player playerDetails) {

        Player player = playerService.getPlayerById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException(PLAYER, ID, playerId));

        player.setBirthdate(playerDetails.getBirthdate());
        player.setCareerStartDate(playerDetails.getCareerStartDate());
        
        return playerService.addOrUpdatePlayer(player);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable(value = ID) Long playerId) {
        Player player = playerService.getPlayerById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException(PLAYER, ID, playerId));

        playerService.deletePlayer(player.getPlayerId());

        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/getTeams")
    public List<Team> getTeamsOfPlayer(@RequestParam Long playerId) {
    	List<Team> teams = playerService.getTeamsOfPlayer(playerId);
    	
    	if (CollectionUtils.isEmpty(teams)) {
			throw new ResourceNotFoundException(TEAMS_OF_PLAYER, ID, playerId); 
		}
    	
    	return teams;
    }
}
