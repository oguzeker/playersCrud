package com.sosyal.tr.playersCrud.repository;

import java.util.List;

import com.sosyal.tr.playersCrud.entity.Player;

public interface TeamRepositoryCustom {

	public List<Player> getPlayersOfTeam(Long id, Long year);
	
}
