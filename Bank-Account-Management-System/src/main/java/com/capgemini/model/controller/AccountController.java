package com.capgemini.model.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.capgemini.model.entity.Account;
import com.capgemini.model.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	AccountService accountService;
	
	// SAVE SINGLE ACCOUNT
	@PostMapping("/saveAccount")
	public void postAccount(@RequestBody Account account) {
		accountService.saveAccount(account);
	}
	
	// SAVE MULTIPLE ACCOUNTS
	@PostMapping("/saveAccounts")
	public void postAccounts(@RequestBody List<Account> accounts) {
		accountService.saveAccounts(accounts);
	}
	
	// GET ACCOUNT BY ID
	@GetMapping("/getAccount/{id}")
	public Account getAccount(@PathVariable Integer id) {
		return accountService.getAccount(id);
	}
	
	// GET ALL ACCOUNTS
	@GetMapping("/getAccounts")
	public List<Account> getAccounts() {
		return accountService.getAccounts();
	}
	
	// FULL UPDATE (PUT)
	@PutMapping("/completeUpdateAccount/{id}")
	public Account putAccount(@PathVariable Integer id, @RequestBody Account account) {
		return accountService.updateAccount(id, account);
	}
	
	// PARTIAL UPDATE (PATCH)
	@PatchMapping("/partialUpdateAccount/{id}")
	public Account patchAccount(@PathVariable Integer id, @RequestBody Map<String, Object> fields) {
		return accountService.updateAccount(id, fields);
	}
	
	// DELETE ACCOUNT
	@DeleteMapping("/deleteAccount/{id}")
	public void deleteAccount(@PathVariable Integer id) {
		accountService.deleteAccount(id);
	}
	
	// DEPOSIT
	@PostMapping("/deposit/{id}")
	public Account deposit(@PathVariable Integer id, @RequestBody Map<String, Object> request) {
		Double amount = ((Number) request.get("amount")).doubleValue();
		return accountService.deposit(id, amount);
	}
	
	// WITHDRAW
	@PostMapping("/withdraw/{id}")
	public Account withdraw(@PathVariable Integer id, @RequestBody Map<String, Object> request) {
		Double amount = ((Number) request.get("amount")).doubleValue();
		return accountService.withdraw(id, amount);
	}
}