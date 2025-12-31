package com.example.springboot_hexagonal_crud.application.controller;

import com.example.springboot_hexagonal_crud.domain.ports.input.OrderServicePort;
import com.example.springboot_hexagonal_crud.application.dto.OrderDto;
import com.example.springboot_hexagonal_crud.infra.mappers.OrderMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/orders")
@Validated
public class OrderController {
    private final OrderServicePort orderServicePort;
    private final OrderMapper orderMapper;

    public OrderController(OrderServicePort orderServicePort, OrderMapper orderMapper) {
        this.orderServicePort = orderServicePort;
        this.orderMapper = orderMapper;
    }

    @PostMapping
    public ResponseEntity<OrderDto> create(@RequestBody @Validated OrderDto dto) {
        var order = orderMapper.toDomain(dto);
        var saved = orderServicePort.create(order);
        var savedDto = orderMapper.toDto(saved);
        return ResponseEntity.created(URI.create("/api/orders/" + savedDto.getId())).body(savedDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<OrderDto> update(@PathVariable Long id, @RequestBody @Validated OrderDto dto) {
        var order = orderMapper.toDomain(dto);
        var updated = orderServicePort.update(id, order);
        return ResponseEntity.ok(orderMapper.toDto(updated));
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> findAll() {
        var list = orderServicePort.findAll().stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderDto> findById(@PathVariable Long id) {
        return orderServicePort.findById(id)
                .map(orderMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderServicePort.delete(id);
        return ResponseEntity.noContent().build();
    }

    //testando update
}
