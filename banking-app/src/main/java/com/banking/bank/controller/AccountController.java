package com.banking.bank.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banking.bank.dto.Accountdto;
import com.banking.bank.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	private AccountService accountService;

	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}
	
	//Add account rest api
	@PostMapping
	public ResponseEntity<Accountdto> addAccount(@RequestBody Accountdto accountdto) {
		return new ResponseEntity<>(accountService.createAccount(accountdto), HttpStatus.CREATED);
	}
	
	//Get Account Rest Api
	@GetMapping("/{id}")
	public ResponseEntity<Accountdto> getAccountById(@PathVariable("id") Long id) {
		Accountdto accountById = accountService.getAccountById(id);
		return ResponseEntity.ok(accountById);
	}
	
	//Deposit Rest Api
	@PutMapping("/{id}/deposit")
	public ResponseEntity<Accountdto> deposit(@PathVariable Long id,
												@RequestBody Map<String, Double> request) {
		
		Double amount = request.get("amount");
		Accountdto accountdto = accountService.depositAccount(id, amount);
		return ResponseEntity.ok(accountdto);
	}
	
	//Withdraw Rest Api
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<Accountdto> withdraw(@PathVariable Long id,
												@RequestBody Map<String, Double> request) {
		double amount = request.get("amount");
		Accountdto withdrawAmount = accountService.withdrawAmount(id, amount);
		return ResponseEntity.ok(withdrawAmount);
	}
	
	//GetAll Account
	@GetMapping
	public ResponseEntity<List<Accountdto>> getAllAccounts() {
		List<Accountdto> accounts = accountService.getAllAccounts();
		return ResponseEntity.ok(accounts);
	}
	
	//Delete Account
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account Deleted Successfully");
	}
	
}
