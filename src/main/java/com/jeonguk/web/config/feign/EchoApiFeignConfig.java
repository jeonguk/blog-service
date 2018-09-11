package com.jeonguk.web.config.feign;

import com.google.gson.Gson;
import com.jeonguk.web.config.feign.exception.EchoApiException;
import feign.Client;
import feign.Response;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.httpclient.ApacheHttpClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@AllArgsConstructor
@Configuration
public class EchoApiFeignConfig {

    private final Gson gson;

    @Bean
    public Client client() {
        final HttpClientBuilder builder = HttpClientBuilder.create()
            .setMaxConnPerRoute(100)
            .setMaxConnTotal(100);
        return new ApacheHttpClient(builder.build());
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new EchoApiFeignConfig.EchoApiExceptionHandler();
    }

    @Bean
    public Decoder decoder() {
        return new GsonDecoder(gson);
    }

    @Bean
    public Encoder encoder() {
        return new GsonEncoder(gson);
    }

    @Slf4j
    public static class EchoApiExceptionHandler implements ErrorDecoder {
        @Override
        public Exception decode(String methodKey, Response response) {
            final String body;
            EchoApiException.ErrorResponse errorResponse = null;
            try {
                body = IOUtils.toString(response.body().asInputStream(), StandardCharsets.UTF_8.name());
                log.error("EchoApi ErrorResponse : {}", body);
                errorResponse = new Gson().fromJson(body, EchoApiException.ErrorResponse.class);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
            return new EchoApiException(errorResponse, response.status());
        }
    }
}
