package com.sosyal.tr.playersCrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sosyal.tr.playersCrud.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

}
