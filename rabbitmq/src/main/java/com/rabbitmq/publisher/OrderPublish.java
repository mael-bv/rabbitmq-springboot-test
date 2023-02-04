package com.rabbitmq.publisher;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.config.MessageConfig;
import com.rabbitmq.dto.Order;
import com.rabbitmq.dto.OrderStatus;
@RestController
@RequestMapping("/order")
public class OrderPublish {
	
	@Autowired
	private RabbitTemplate template;
	
	@PostMapping("/{resto}")
	public String bookOrder(Order order,@PathVariable String resto ) {
		order.setOrderId(UUID.randomUUID().toString());
		
		OrderStatus orderStatus = new OrderStatus(order,"Process", "transfer avec succès in "+ resto);
		template.convertAndSend(MessageConfig.ECHANGE, MessageConfig.ROUTING_KEY, orderStatus);
		return "ok";
	}
}
