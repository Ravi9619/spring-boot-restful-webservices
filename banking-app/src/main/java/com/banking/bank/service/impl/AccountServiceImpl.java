package com.banking.bank.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.banking.bank.dto.Accountdto;
import com.banking.bank.entity.Account;
import com.banking.bank.mapper.AccountMapper;
import com.banking.bank.repository.AccountRepository;
import com.banking.bank.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	private AccountRepository accountRepository;

	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public Accountdto createAccount(Accountdto accountdto) {
		Account account = AccountMapper.mapToAccount(accountdto);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountdto(savedAccount);
	}

	@Override
	public Accountdto getAccountById(Long id) {
		Account account = accountRepository
							.findById(id)
							.orElseThrow(() -> new RuntimeException("Account does not exists"));
		return AccountMapper.mapToAccountdto(account);
	}

	@Override
	public Accountdto depositAccount(Long id, double amount) {
		Account account = accountRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exists"));
		
		double total = account.getBalance() + amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountdto(savedAccount);
	}

	@Override
	public Accountdto withdrawAmount(Long id, double amount) {
		Account account = accountRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exists"));
		
		if(account.getBalance()< amount) {
			throw new RuntimeException("Insufficient Amount");
		}
		
		double total = account.getBalance() - amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountdto(savedAccount);
	}

	@Override
	public List<Accountdto> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		return accounts.stream().map((account) -> 
								AccountMapper
								.mapToAccountdto(account))
								.collect(Collectors.toList());
		
	}

	@Override
	public void deleteAccount(Long id) {
		Account account = accountRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exists"));
		
		accountRepository.deleteById(id);
	}
	
}
