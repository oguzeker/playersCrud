package com.sosyal.tr.playersCrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sosyal.tr.playersCrud.entity.Player;
import com.sosyal.tr.playersCrud.entity.Team;
import com.sosyal.tr.playersCrud.repository.TeamRepository;
import com.sosyal.tr.playersCrud.repository.impl.TeamRepositoryImpl;

@Service
public class TeamService {

	@Autowired
    private TeamRepository teamDao;
	
	@Autowired
	private TeamRepositoryImpl teamDaoImpl;

    public Team addOrUpdateTeam(Team team) {
        return teamDao.save(team);
    }

    public List<Team> getAllTeams() {
        return teamDao.findAll();
    }

    public Optional<Team> getTeamById(Long id) {
        return teamDao.findById(id);
    }

    public void deleteTeam(Long id) {
        teamDao.deleteById(id);
    }
    
    public List<Player> getPlayersOfTeam(Long id, Long year) {
    	return teamDaoImpl.getPlayersOfTeam(id, year);
    }

}
