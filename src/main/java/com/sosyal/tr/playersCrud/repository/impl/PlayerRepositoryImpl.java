package com.sosyal.tr.playersCrud.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sosyal.tr.playersCrud.entity.Contract;
import com.sosyal.tr.playersCrud.entity.Contract_;
import com.sosyal.tr.playersCrud.entity.Player;
import com.sosyal.tr.playersCrud.entity.Player_;
import com.sosyal.tr.playersCrud.entity.Team;
import com.sosyal.tr.playersCrud.entity.Team_;
import com.sosyal.tr.playersCrud.repository.PlayerRepository;
import com.sosyal.tr.playersCrud.repository.PlayerRepositoryCustom;

@Component
public class PlayerRepositoryImpl implements PlayerRepositoryCustom {

	@PersistenceContext
    private EntityManager em;
	
	@SuppressWarnings("unused")
	@Autowired
	private PlayerRepository playerRepository;

	@Override
	public List<Team> getTeamsOfPlayer(Long id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Team> query = cb.createQuery(Team.class);
		Root<Team> team = query.from(Team.class);
		ListJoin<Team, Contract> contracts = team.join(Team_.contracts);
		Join<Contract, Player> player = contracts.join(Contract_.player);

		query.where(cb.equal(player.get(Player_.playerId), id));
		query.distinct(true);
		
		List<Team> resultList = em.createQuery(query)
				.getResultList();
		
		return resultList;
	}
	

}
