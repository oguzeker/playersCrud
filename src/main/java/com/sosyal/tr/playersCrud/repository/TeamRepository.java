package com.sosyal.tr.playersCrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sosyal.tr.playersCrud.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

}
