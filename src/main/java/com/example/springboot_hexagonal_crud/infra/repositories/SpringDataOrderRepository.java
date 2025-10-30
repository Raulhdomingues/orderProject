package com.example.springboot_hexagonal_crud.infra.repositories;

import com.example.springboot_hexagonal_crud.infra.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataOrderRepository extends JpaRepository<OrderEntity, Long> {
}
