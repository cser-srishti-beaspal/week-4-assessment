package com.capgemini.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.entity.Order;
import com.capgemini.model.repository.OrderRepository;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    // SAVE SINGLE
    public void saveOrder(Order order) {
        if (order.getQuantity() <= 0) {
            System.out.println("Quantity must be greater than 0");
            return;
        }

        order.setStatus("PLACED");
        orderRepository.save(order);
        System.out.println("Order Saved Successfully!");
    }

    // SAVE MULTIPLE
    public void saveOrders(List<Order> orders) {
        for (Order order : orders) {
            if (order.getQuantity() <= 0) {
                System.out.println("Invalid quantity for order");
                return;
            }
            order.setStatus("PLACED");
        }
        orderRepository.saveAll(orders);
        System.out.println("Orders Saved Successfully!");
    }

    // GET BY ID
    public Order getOrder(Integer id) {
        return orderRepository.findById(id).get(); // same as your employee code
    }

    // GET ALL
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    // FULL UPDATE (PUT)
    public Order updateOrder(Integer id, Order newOrder) {
        Order existing = orderRepository.findById(id).get();
        if ("CANCELLED".equals(existing.getStatus())) {
            System.out.println("Cannot update cancelled order");
            return existing;
        }
        if (newOrder.getQuantity() <= 0) {
            System.out.println("Quantity must be greater than 0");
            return existing;
        }
        if (newOrder != null) {
            existing.setCustomerName(newOrder.getCustomerName());
            existing.setFoodItem(newOrder.getFoodItem());
            existing.setQuantity(newOrder.getQuantity());
        }
        return orderRepository.save(existing);
    }

    // DELETE (SOFT DELETE)
    public void deleteOrder(Integer id) {

        Order existing = orderRepository.findById(id).get();
        // Soft delete
        existing.setStatus("CANCELLED");
        orderRepository.save(existing);
        System.out.println("Order Cancelled Successfully!");
    }

    // PARTIAL UPDATE (PATCH)
    public Order updateOrder(Integer id, Map<String, Object> fields) {

        Order existing = orderRepository.findById(id).get();
        if ("CANCELLED".equals(existing.getStatus())) {
            System.out.println("Cannot update cancelled order");
            return existing;
        }
        for (Map.Entry<String, Object> field : fields.entrySet()) {
            String key = field.getKey();
            Object value = field.getValue();
            if (key.equals("customerName")) {
                existing.setCustomerName((String) value);
            }
            if (key.equals("foodItem")) {
                existing.setFoodItem((String) value);
            }
            if (key.equals("quantity")) {
                int qty = (Integer) value;
                if (qty <= 0) {
                    System.out.println("Quantity must be greater than 0");
                    return existing;
                }
                existing.setQuantity(qty);
            }
        }
        return orderRepository.save(existing);
    }
}
