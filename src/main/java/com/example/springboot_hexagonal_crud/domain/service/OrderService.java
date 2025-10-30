package com.example.springboot_hexagonal_crud.domain.service;

import com.example.springboot_hexagonal_crud.domain.model.Order;
import com.example.springboot_hexagonal_crud.domain.ports.input.OrderServicePort;
import com.example.springboot_hexagonal_crud.domain.ports.output.OrderRepositoryPort;
import com.example.springboot_hexagonal_crud.infra.messaging.OrderEventProducer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements OrderServicePort {

    private final OrderRepositoryPort repository;
    private final OrderEventProducer producer;

    public OrderService(OrderRepositoryPort repository, OrderEventProducer producer) {
        this.repository = repository;
        this.producer = producer;
    }

    @Override
    public Order create(Order order) {
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus("CREATED");
        var saved = repository.save(order);
        producer.publishOrderCreated(saved);
        return saved;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }

    @Override
    public Order update(Long id, Order order) {
        var opt = repository.findById(id);
        if(opt.isEmpty()) throw new RuntimeException("Order not found");
        var existing = opt.get();
        existing.setCustomerName(order.getCustomerName());
        existing.setAmount(order.getAmount());
        existing.setStatus(order.getStatus());
        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
