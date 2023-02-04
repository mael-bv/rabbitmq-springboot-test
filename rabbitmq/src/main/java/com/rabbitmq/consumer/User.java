package com.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.config.MessageConfig;
import com.rabbitmq.dto.OrderStatus;

@Component
public class User {
	
	@RabbitListener(queues = MessageConfig.QUEUE)
	public void consumerMessageFromQueue(OrderStatus orderStatus) {
		System.out.println("Messag reçu from queue : " + orderStatus);
	}

}
