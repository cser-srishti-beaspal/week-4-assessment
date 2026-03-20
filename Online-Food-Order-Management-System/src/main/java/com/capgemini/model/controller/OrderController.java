package com.capgemini.model.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.capgemini.model.entity.Order;
import com.capgemini.model.service.OrderService;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    // SAVE SINGLE ORDER
    @PostMapping("/saveOrder")
    public void saveOrder(@RequestBody Order order) {
        orderService.saveOrder(order);
    }

    // SAVE MULTIPLE ORDERS
    @PostMapping("/saveOrders")
    public void saveOrders(@RequestBody List<Order> orders) {
        orderService.saveOrders(orders);
    }

    // GET ORDER BY ID
    @GetMapping("/getOrder/{id}")
    public Order getOrder(@PathVariable Integer id) {
        return orderService.getOrder(id);
    }

    // GET ALL ORDERS
    @GetMapping("/getOrders")
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    // FULL UPDATE (PUT)
    @PutMapping("/completeUpdateOrder/{id}")
    public Order updateOrder(@PathVariable Integer id, @RequestBody Order order) {
        return orderService.updateOrder(id, order);
    }

    // PARTIAL UPDATE (PATCH)
    @PatchMapping("/partialUpdateOrder/{id}")
    public Order patchOrder(@PathVariable Integer id, @RequestBody Map<String, Object> fields) {
        return orderService.updateOrder(id, fields);
    }

    // DELETE (SOFT DELETE → STATUS:UPDATE)
    @DeleteMapping("/deleteOrder/{id}")
    public void deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
    }
}