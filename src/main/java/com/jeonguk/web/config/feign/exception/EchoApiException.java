package com.jeonguk.web.config.feign.exception;

import lombok.Data;
import lombok.Getter;

@Getter
public class EchoApiException extends RuntimeException {
    @Data
    public static class ErrorResponse {
        private Integer status;
        private String message;
    }

    private final transient ErrorResponse errorResponse;
    private final Integer status;

    public EchoApiException(ErrorResponse response, Integer status) {
        super("EchoApiException");
        this.errorResponse = response;
        this.status = status;
    }
}