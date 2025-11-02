package com.example.orderservice.controller;

import com.example.orderservice.kafka.OrderProducer;
import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderRepository repo;
   private final OrderProducer producer;

    public OrderController(OrderRepository repo, OrderProducer producer) {
        this.repo = repo;
        this.producer = producer;
    }


    @PostMapping

    public ResponseEntity<Order> createOrder(@RequestBody Order order) {

        Order saved = repo.save(order);
        producer.send(saved);

        return ResponseEntity.ok(saved);

    }

    @GetMapping
    public ResponseEntity<java.util.List<Order>> list() {
        return ResponseEntity.ok(repo.findAll());
}
}
