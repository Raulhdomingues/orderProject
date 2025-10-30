package com.example.springboot_hexagonal_crud.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    private String customerName;
    private String amount;
    private String status;
    private LocalDateTime createdAt;
}
