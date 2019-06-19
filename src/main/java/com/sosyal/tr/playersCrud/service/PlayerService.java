package com.sosyal.tr.playersCrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sosyal.tr.playersCrud.entity.Player;
import com.sosyal.tr.playersCrud.entity.Team;
import com.sosyal.tr.playersCrud.repository.PlayerRepository;
import com.sosyal.tr.playersCrud.repository.impl.PlayerRepositoryImpl;

@Service
public class PlayerService {

	@Autowired
    private PlayerRepository playerDao;
	@Autowired
	private PlayerRepositoryImpl playerDaoImpl;

    public Player addOrUpdatePlayer(Player player) {
        return playerDao.save(player);
    }

    public List<Player> getAllPlayers() {
        return playerDao.findAll();
    }

    public Optional<Player> getPlayerById(Long id) {
        return playerDao.findById(id);
    }

    public void deletePlayer(Long id) {
        playerDao.deleteById(id);
    }

    public List<Team> getTeamsOfPlayer(Long id) {
    	return playerDaoImpl.getTeamsOfPlayer(id);
    }
    
}
