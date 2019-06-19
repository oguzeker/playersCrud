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
import com.sosyal.tr.playersCrud.repository.TeamRepository;
import com.sosyal.tr.playersCrud.repository.TeamRepositoryCustom;

@Component
public class TeamRepositoryImpl implements TeamRepositoryCustom {

	@PersistenceContext
    private EntityManager em;
	
	@SuppressWarnings("unused")
	@Autowired
	private TeamRepository teamRepository;

	@Override
	public List<Player> getPlayersOfTeam(Long id, Long season) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Player> query = cb.createQuery(Player.class);
		Root<Player> player = query.from(Player.class);
		ListJoin<Player, Contract> contracts = player.join(Player_.contracts);
		Join<Contract, Team> team = contracts.join(Contract_.team);

		query.where(cb.and(
				cb.equal(team.get(Team_.teamId), id),
				cb.equal(contracts.get(Contract_.season), season)));
		query.distinct(true);
		
		List<Player> resultList = em.createQuery(query)
				.getResultList();
		
		return resultList;
	}
	

}
