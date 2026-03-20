package com.capgemini.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.entity.Account;
import com.capgemini.model.repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	AccountRepository accountRepository;
	
	public void saveAccount(Account account) {
		accountRepository.save(account);
		System.out.println("Account Saved Successfully!");
	}
	
	public void saveAccounts(List<Account> accounts) {
		accountRepository.saveAll(accounts);
		System.out.println("Accounts Saved Successfully!");
	}
	
	public Account getAccount(Integer id) {
		return accountRepository.findById(id).get();
	}
	
	public List<Account> getAccounts() {
		return accountRepository.findAll();
	}
	
	public Account updateAccount(Integer id, Account newAccount) {
		Account existing = accountRepository.findById(id).get();
		if(newAccount != null) {
			if(existing != null) {
				existing.setAccountHolderName(newAccount.getAccountHolderName());
				existing.setBalance(newAccount.getBalance());
			}
		}
		return accountRepository.save(existing);
	}
	
	public void deleteAccount(Integer id) {
		Account existing = accountRepository.findById(id).get();
		accountRepository.deleteById(id);
	}
	
	public Account updateAccount(Integer id, Map<String, Object> fields) {
		Account existing = accountRepository.findById(id).get();
		for(Map.Entry<String, Object> field : fields.entrySet()) {
			String key = field.getKey();
			Object value = field.getValue();
			
			if(key.equals("accountHolderName")) {
				existing.setAccountHolderName((String) value);
			}
			
			if(key.equals("balance")) {
				existing.setBalance(((Number) value).doubleValue());
			}
		}
		return accountRepository.save(existing);
	}
	
	public Account deposit(Integer id, Double amount) {
		Account existing = accountRepository.findById(id).get();
		existing.setBalance(existing.getBalance() + amount);
		return accountRepository.save(existing);
	}
	
	public Account withdraw(Integer id, Double amount) {
		Account existing = accountRepository.findById(id).get();
		existing.setBalance(existing.getBalance() - amount);
		return accountRepository.save(existing);
	}
}