package com.example.springboot_hexagonal_crud.infra.adapters;

import com.example.springboot_hexagonal_crud.domain.model.Order;
import com.example.springboot_hexagonal_crud.domain.ports.output.OrderRepositoryPort;
import com.example.springboot_hexagonal_crud.infra.mappers.OrderMapper;
import com.example.springboot_hexagonal_crud.infra.repositories.SpringDataOrderRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderRepositoryAdapter implements OrderRepositoryPort {

    private final SpringDataOrderRepository springRepo;
    private final OrderMapper mapper;

    public OrderRepositoryAdapter(SpringDataOrderRepository springRepo, OrderMapper mapper) {
        this.springRepo = springRepo;
        this.mapper = mapper;
    }

    @Override
    public Order save(Order order) {
        var entity = mapper.toEntity(order);
        var saved = springRepo.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return springRepo.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Order> findAll() {
        return springRepo.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        springRepo.deleteById(id);
    }
}
