package com.jeonguk.web.config.feign.exception;

import lombok.Data;
import lombok.Getter;

@Getter
public class ExtApiException extends RuntimeException {
    @Data
    public static class ErrorResponse {
        private Long code;
        private String msg;
    }

    private final transient ExtApiException.ErrorResponse errorResponse;
    private final Integer status;

    public ExtApiException(ExtApiException.ErrorResponse response, Integer status) {
        super();
        this.errorResponse = response;
        this.status = status;
    }
}