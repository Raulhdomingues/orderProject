package com.example.springboot_hexagonal_crud.infra.messaging;

import com.example.springboot_hexagonal_crud.domain.model.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component

public class OrderEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public OrderEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishOrderCreated(Order order){
        try {
            var payload = new HashMap<String, Object>();
            payload.put("id", order.getId());
            payload.put("customerName", order.getCustomerName());
            payload.put("amount", order.getAmount());
            rabbitTemplate.convertAndSend("order.exchange", "order.created", payload);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
