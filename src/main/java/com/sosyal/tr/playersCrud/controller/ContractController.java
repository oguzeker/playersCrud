package com.sosyal.tr.playersCrud.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sosyal.tr.playersCrud.entity.Contract;
import com.sosyal.tr.playersCrud.exception.ResourceNotFoundException;
import com.sosyal.tr.playersCrud.service.ContractService;

@RestController
@RequestMapping("/contract")
public class ContractController {

    private static final String ID = "id";
	private static final String CONTRACT = "Contract";
	
	@Autowired
    private ContractService contractService;

    @GetMapping
    public List<Contract> getAllContracts() {
        return contractService.getAllContracts();
    }

    @PostMapping
    public Contract createContract(@Valid @RequestBody Contract contract) {
    	contract.setContractId(null);
        return contractService.addOrUpdateContract(contract);
    }

    @GetMapping("{id}")
    public Contract getContractById(@PathVariable(value = ID) Long contractId) {
        return contractService.getContractById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException(CONTRACT, ID, contractId));
    }

    @PutMapping("{id}")
    public Contract updateContract(@PathVariable(value = ID) Long contractId,
                                           @Valid @RequestBody Contract contractDetails) {

        Contract contract = contractService.getContractById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException(CONTRACT, ID, contractId));

        contract.setTransferPrice(contractDetails.getTransferPrice());
        contract.setTeamCommission(contractDetails.getTeamCommission());
        contract.setSeason(contractDetails.getSeason());
        contract.setPlayer(contractDetails.getPlayer());
        contract.setTeam(contractDetails.getTeam());
        
        return contractService.addOrUpdateContract(contract);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteContract(@PathVariable(value = ID) Long contractId) {
        Contract contract = contractService.getContractById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException(CONTRACT, ID, contractId));

        contractService.deleteContract(contract.getContractId());

        return ResponseEntity.ok().build();
    }
}
