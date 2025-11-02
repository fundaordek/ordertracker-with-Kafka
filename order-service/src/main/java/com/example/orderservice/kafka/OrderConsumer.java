package com.example.orderservice.kafka;

import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {
    private final OrderRepository repo;

    public OrderConsumer(OrderRepository repo) {
        this.repo = repo;
    }

    @KafkaListener(topics = "orders", groupId = "order-group")
    public void consume(Order order) {
        // save to mongo (id may be null when produced; repository will generate)
        System.out.println("[Consumer] Got order: " + order.getProduct() + " x" + order.getQuantity());
        repo.save(order);
    }
}
