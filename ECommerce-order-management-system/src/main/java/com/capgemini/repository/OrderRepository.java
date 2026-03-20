package com.capgemini.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.model.Order;
import com.capgemini.model.OrderStatus;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Page<Order> findByCustomerCustomerIdAndStatus(
        Integer customerId, OrderStatus status, Pageable pageable);

    @Query("""
        SELECT c.fullName, SUM(o.totalAmount)
        FROM Order o JOIN o.customer c
        WHERE o.status = 'DELIVERED'
        GROUP BY c.fullName
    """)
    List<Object[]> getRevenueSummary();
}
