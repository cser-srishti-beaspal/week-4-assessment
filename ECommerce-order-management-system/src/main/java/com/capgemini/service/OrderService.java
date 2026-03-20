package com.capgemini.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.capgemini.model.Customer;
import com.capgemini.model.Order;
import com.capgemini.model.OrderStatus;
import com.capgemini.repository.CustomerRepository;
import com.capgemini.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private CustomerRepository customerRepo;

    public Order placeOrder(Integer customerId, Order order) {

        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // 🔥 Derived field
        order.setTotalAmount(
            order.getPricePerUnit()
                .multiply(BigDecimal.valueOf(order.getQuantity()))
        );

        order.setCustomer(customer);

        return orderRepo.save(order);
    }

    public Page<Order> getOrders(Integer customerId, OrderStatus status,
                                int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return orderRepo.findByCustomerCustomerIdAndStatus(
                customerId, status, pageable);
    }

    public Order cancelOrder(Integer orderId) {

        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getStatus() == OrderStatus.SHIPPED ||
            order.getStatus() == OrderStatus.DELIVERED) {
            throw new RuntimeException("Cannot cancel delivered/shipped order");
        }

        order.setStatus(OrderStatus.CANCELLED);

        return orderRepo.save(order);
    }

    public List<Map<String, Object>> revenueSummary() {

        List<Object[]> data = orderRepo.getRevenueSummary();

        return data.stream().map(row ->
                Map.of(
                    "customerName", row[0],
                    "totalRevenue", row[1]
                )
        ).toList();
    }
}
