package com.sosyal.tr.playersCrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sosyal.tr.playersCrud.entity.Contract;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

}
