package com.transaction.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transaction.springboot.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
