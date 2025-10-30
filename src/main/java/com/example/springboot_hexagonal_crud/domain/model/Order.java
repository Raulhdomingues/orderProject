package com.example.springboot_hexagonal_crud.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Order {
    private Long id;
    private String customerName;
    private BigDecimal amount;
    private String status;
    private LocalDateTime createdAt;
}
