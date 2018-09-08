package com.jeonguk.web.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * throw new CustomException(CustomStatus.RESOURCE_NOT_FOUND)
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 3779124259645870652L;

	private String name;
	private int status;
	private String reasonPhrase;

	public CustomException() {
		super();
	}

	public CustomException(CustomStatus customStatus) {
		this.name = customStatus.name();
		this.status = customStatus.getValue();
		this.reasonPhrase = customStatus.getReasonPhrase();
	}

}