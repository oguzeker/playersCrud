package com.sosyal.tr.playersCrud.repository;

import java.util.List;

import com.sosyal.tr.playersCrud.entity.Team;

public interface PlayerRepositoryCustom {

	public List<Team> getTeamsOfPlayer(Long id);
	
}
