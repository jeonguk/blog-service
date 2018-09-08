package com.jeonguk.web.exception;

import lombok.Getter;

public enum  CustomStatus {
	RESOURCE_NOT_FOUND(900, "resource not found!"),
	UNAUTHORIZED_RESOURCE(901, "unauthorized resource")
	;

	@Getter
	private final int value;

	@Getter
	private final String reasonPhrase;

	CustomStatus(int value, String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
	}
}
