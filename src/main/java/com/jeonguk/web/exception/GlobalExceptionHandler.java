package com.jeonguk.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String STANDARD_SPRING_EXCEPTION = "STANDARD_SPRING_EXCEPTION";

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
	                                                         HttpStatus status, WebRequest request) {
		log.error(STANDARD_SPRING_EXCEPTION, ex);
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	                                                              HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error(HttpStatus.BAD_REQUEST.name(), ex);
		final StringBuilder sb = new StringBuilder();
		ex.getBindingResult().getFieldErrors().forEach(fieldError -> sb.append(fieldError.getField()).append(" : ")
				.append(fieldError.getDefaultMessage()).append("\n"));
		return super.handleExceptionInternal(ex, sb.toString(), headers, status, request);
	}

	@ExceptionHandler(CustomException.class)
	public @ResponseBody CustomExceptionResponse handle(HttpServletResponse response, CustomException ex) {
		response.setStatus(ex.getStatus());
		return CustomExceptionResponse.valueOf(ex);
	}
}
