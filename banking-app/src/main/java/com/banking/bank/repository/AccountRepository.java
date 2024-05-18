package com.banking.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.bank.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
