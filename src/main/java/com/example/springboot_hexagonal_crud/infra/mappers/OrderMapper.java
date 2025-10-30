package com.example.springboot_hexagonal_crud.infra.mappers;

import com.example.springboot_hexagonal_crud.domain.model.Order;
import com.example.springboot_hexagonal_crud.application.dto.OrderDto;
import com.example.springboot_hexagonal_crud.infra.entities.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toDomain(OrderEntity entity);
    OrderEntity toEntity(Order domain);
    OrderDto toDto(Order domain);
    Order toDomain(OrderDto dto);
}
