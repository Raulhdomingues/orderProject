package com.example.springboot_hexagonal_crud.domain.ports.output;

import com.example.springboot_hexagonal_crud.domain.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepositoryPort {
    Order save(Order order);
    Optional<Order> findById(Long id);
    List<Order> findAll();
    void deleteById(Long id);
}
