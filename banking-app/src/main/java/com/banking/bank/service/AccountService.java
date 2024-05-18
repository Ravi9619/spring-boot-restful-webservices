package com.banking.bank.service;

import java.util.List;

import com.banking.bank.dto.Accountdto;

public interface AccountService {
	
	Accountdto createAccount(Accountdto accountdto);
	
	Accountdto getAccountById(Long id);
	
	Accountdto depositAccount(Long id, double amount);
	
	Accountdto withdrawAmount(Long id, double amount);
	
	List<Accountdto> getAllAccounts();
	
	void deleteAccount(Long id);

}
