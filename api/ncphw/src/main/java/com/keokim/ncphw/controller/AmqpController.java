package com.keokim.ncphw.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.keokim.ncphw.dto.response.NcpHwResponse;
import com.keokim.ncphw.queue.TestProducer;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RequestMapping("/amqp")
@RestController
@RequiredArgsConstructor
public class AmqpController {

	private final TestProducer testProducer;

	@GetMapping("/message")
	public NcpHwResponse<?> produce(@NotNull @RequestParam String cont) {
		testProducer.produce(cont);
		return NcpHwResponse.success();
	}
}
