package com.example.springboot_hexagonal_crud.domain.ports.input;

import com.example.springboot_hexagonal_crud.domain.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderServicePort {
    Order create(Order order);
    Optional<Order> findById(Long id);
    List<Order> findAll();
    Order update(Long id, Order order);
    void delete(Long id);
}
