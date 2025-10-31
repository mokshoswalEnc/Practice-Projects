package com.order.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.order.model.Order;
import com.order.service.Inventory;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private Inventory inv;

    private final List<Order> orderList = new ArrayList<>();

    @PostMapping("/create")
    public String createOrder(@RequestBody Order order) {
        order.setStatus("Created");
        orderList.add(order);
        return "Order created successfully with ID: " + order.getOId();
    }

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return orderList;
    }

    @GetMapping("/kafka/messages")
    public List<String> getKafkaMessages() {
        return inv.getMessages();
    }
}
