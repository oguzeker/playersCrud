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
import com.sosyal.tr.playersCrud.service.TeamService;

@RestController
@RequestMapping("/team")
public class TeamController {

    private static final String PLAYERS_OF_TEAM = "PLAYERS OF TEAM";
	private static final String ID = "id";
	private static final String TEAM = "Team";
	
	@Autowired
    private TeamService teamService;

    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @PostMapping
    public Team createTeam(@Valid @RequestBody Team team) {
    	team.setTeamId(null);
        return teamService.addOrUpdateTeam(team);
    }

    @GetMapping("{id}")
    public Team getTeamById(@PathVariable(value = ID) Long teamId) {
        return teamService.getTeamById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException(TEAM, ID, teamId));
    }

    @PutMapping("{id}")
    public Team updateTeam(@PathVariable(value = ID) Long teamId,
                                           @Valid @RequestBody Team teamDetails) {

        Team team = teamService.getTeamById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException(TEAM, ID, teamId));

        team.setCurrency(teamDetails.getCurrency());
        
        return teamService.addOrUpdateTeam(team);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable(value = ID) Long teamId) {
        Team team = teamService.getTeamById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException(TEAM, ID, teamId));

        teamService.deleteTeam(team.getTeamId());

        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/getPlayers")
    public List<Player> getPlayersOfTeam(@RequestParam Long teamId,
    		@RequestParam Long season) {
    	List<Player> players = teamService.getPlayersOfTeam(teamId, season);
    	
    	if (CollectionUtils.isEmpty(players)) {
			throw new ResourceNotFoundException(PLAYERS_OF_TEAM, ID, teamId); 
		}
    	
    	return players;
    }
    
}
