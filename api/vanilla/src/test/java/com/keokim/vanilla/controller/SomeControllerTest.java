package com.keokim.vanilla.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = SomeController.class)
class SomeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getSomeResponseAllArgsConstructorRequest() throws Exception {
		mockMvc.perform(get("/some/all-args-constructor")
				.param("someInt", "10"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.someString").value("Hello, 10"));
	}

	@Test
	void getSomeResponseSetterRequest() throws Exception {
		mockMvc.perform(get("/some/setter")
				.param("someInt", "20"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.someString").value("Hello, 20"));
	}

	@Test
	void getSomeRecordResponse() throws Exception {
		mockMvc.perform(get("/some/record")
				.param("someInt", "30"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.someString").value("Hello, 30"));
	}

	@Test
	void getSomeRecordResponseFailsOnNoParam() throws Exception {
		mockMvc.perform(get("/some/record"))
			.andExpect(status().isBadRequest());
	}
}