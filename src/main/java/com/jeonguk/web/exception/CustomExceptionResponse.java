package com.jeonguk.web.exception;

import lombok.Data;

@Data
public class CustomExceptionResponse {
	private String message;
	public static CustomExceptionResponse valueOf(CustomException e) {
		final CustomExceptionResponse response = new CustomExceptionResponse();
		response.setMessage(e.getReasonPhrase());
		return response;
	}
}