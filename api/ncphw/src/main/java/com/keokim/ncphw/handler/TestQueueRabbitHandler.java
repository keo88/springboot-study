package com.keokim.ncphw.handler;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TestQueueRabbitHandler {

	@RabbitListener(queues = "testQueue")
	public void handleMessage(String message) {
		log.info("Received message from testQueue: {}", message);
	}
}
