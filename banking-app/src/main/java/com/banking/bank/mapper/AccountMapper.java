package com.banking.bank.mapper;

import com.banking.bank.dto.Accountdto;
import com.banking.bank.entity.Account;

public class AccountMapper {
	
	public static Account mapToAccount(Accountdto accountdto) {
		Account account = new Account(
				accountdto.getId(),
				accountdto.getAccountHolderName(),
				accountdto.getBalance()
				);
		
		return account;
	}
	
	public static Accountdto mapToAccountdto(Account account) {
		Accountdto accountdto = new Accountdto(
					account.getId(),
					account.getAccountHolderName(),
					account.getBalance()
				);
		
		return accountdto;
	}
	
}
