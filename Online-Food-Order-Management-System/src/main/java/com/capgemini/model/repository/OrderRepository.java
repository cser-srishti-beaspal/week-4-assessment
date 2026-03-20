package com.capgemini.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.model.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
