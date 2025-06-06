package com.keokim.ncphw.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.keokim.ncphw.dto.response.NcpHwResponse;
import com.keokim.ncphw.queue.TestProducer;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RequestMapping("/test")
@RestController
@RequiredArgsConstructor
public class TestController {

	private final TestProducer testProducer;

	@GetMapping("/message")
	public NcpHwResponse<?> produce(@NotNull @RequestParam String message) {
		testProducer.produce(message);
		return NcpHwResponse.success();
	}
}
