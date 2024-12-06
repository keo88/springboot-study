package com.keokim.playground.base.exception;

import static java.util.Objects.*;

import java.util.Arrays;

import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class PlaygroundExceptionHandler {

	@ExceptionHandler(BindException.class)
	public String parameterException(BindException exception, Model model) {
		final String defaultMessage = exception.getMessage();
		log.error("Exception occurred from request : {}", defaultMessage);

		log.info("Request Parameter Binding Exception 에 대한 에러메세지를 빌드합니다");
		final BindingResult bindingResult = exception.getBindingResult();
		final StringBuilder messageBuilder = new StringBuilder();
		bindingResult.getFieldErrors().forEach(fieldError -> {
			String fieldName = fieldError.getField();
			String rejectedValue = String.valueOf(fieldError.getRejectedValue());
			String additionalMessage;
			Class<?> fieldType = bindingResult.getFieldType(fieldName);
			if (nonNull(fieldType) && fieldType.isEnum()) {
				additionalMessage = "Must be one of " + Arrays.toString(fieldType.getEnumConstants());
			} else {
				additionalMessage = fieldError.getDefaultMessage();
			}
			messageBuilder.append("[")
				.append("Input ").append("'").append(rejectedValue).append("'")
				.append(" for field ").append("'").append(fieldName).append("'")
				.append(" has error, ").append(additionalMessage)
				.append("]\n");
		});

		model.addAttribute("message", messageBuilder.toString());

		return "error";
	}

	@ExceptionHandler(Exception.class)
	public String handleException(Exception exception, Model model) {
		log.error("Exception occurred : {}", exception.getMessage(), exception);
		model.addAttribute("message", exception.getMessage());
		return "error";
	}

}
