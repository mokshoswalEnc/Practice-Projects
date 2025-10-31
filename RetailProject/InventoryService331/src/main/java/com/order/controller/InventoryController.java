package com.order.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.model.Item;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Value("${topic.Inventory}")
	private String InventoryTopic;
	
	private final Map<String, Item> inventory = new HashMap<>();
	
	@PostMapping("/add")
	public String addAnItem(@RequestBody Item item) {
        inventory.put(item.getId(), item);
        kafkaTemplate.send(InventoryTopic, "New item added: " + item.getName());
        return "Item added successfully with ID no: " + item.getId();
    }
	
	@GetMapping("/allItems")
    public Collection<Item> getAllItems() {
        return inventory.values();
    }
	
	@GetMapping("/{id}")
	public Item getItemById(@PathVariable String id) {
		return inventory.getOrDefault(id, null);
	}
	
	@PostMapping("/order")
    public String createOrder(@RequestBody Map<String, String> orderDetails) {
        String message = "Order created for Item ID: " + orderDetails.get("itemId") 
                         + " Quantity: " + orderDetails.get("quantity");
        kafkaTemplate.send(InventoryTopic, message);
        return "Order placed successfully and sent to Kafka.";
    }
}
