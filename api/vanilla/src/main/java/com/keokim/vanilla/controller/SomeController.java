package com.keokim.vanilla.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keokim.vanilla.dto.SomeRecordRequest;
import com.keokim.vanilla.dto.SomeRecordResponse;
import com.keokim.vanilla.dto.SomeAllArgsConstructorRequest;
import com.keokim.vanilla.dto.SomeResponse;
import com.keokim.vanilla.dto.SomeSetterRequest;

import jakarta.validation.Valid;

@RequestMapping("/some")
@RestController
public class SomeController {

	@GetMapping("/all-args-constructor")
	public SomeResponse getSomeResponseAllArgsRequest(@ModelAttribute SomeAllArgsConstructorRequest request) {
		return new SomeResponse("Hello, " + request.getSomeInt(), request.getSomeInt());
	}

	@GetMapping("/setter")
	public SomeResponse getSomeResponseSetterRequest(@ModelAttribute SomeSetterRequest request) {
		return new SomeResponse("Hello, " + request.getSomeInt(), request.getSomeInt());
	}

	@GetMapping("/record")
	public SomeRecordResponse getSomeRecordResponse(@Valid @ModelAttribute SomeRecordRequest request) {
		return SomeRecordResponse.builder()
			.someString("Hello, " + request.someInt())
			.someInt(request.someInt())
			.build();
	}

}
