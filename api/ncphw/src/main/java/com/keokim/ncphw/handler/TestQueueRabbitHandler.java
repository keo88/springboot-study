package com.keokim.ncphw.handler;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.ImmediateRequeueAmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TestQueueRabbitHandler {

	@RabbitListener(queues = "testQueue")
	public void handleMessage(Message message) {
		String content = new String(message.getBody());

		if (StringUtils.contains(content, "retry")) {
			throw new ImmediateRequeueAmqpException("error");
		} else if (StringUtils.contains(content, "error")) {
			throw new AmqpRejectAndDontRequeueException("Error processing message: " + content);
		} else if (StringUtils.contains(content, "runtime")) {
			throw new RuntimeException("Fail processing message: " + content);
		}

		log.info("Received message from testQueue: {}", message);
	}
}
