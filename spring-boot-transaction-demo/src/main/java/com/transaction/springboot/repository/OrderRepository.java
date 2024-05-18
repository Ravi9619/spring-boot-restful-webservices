package com.transaction.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transaction.springboot.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
