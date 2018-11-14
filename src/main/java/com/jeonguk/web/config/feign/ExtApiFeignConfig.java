package com.jeonguk.web.config.feign;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.jeonguk.web.config.feign.exception.ExtApiException;
import feign.Client;
import feign.Feign;
import feign.Logger;
import feign.codec.ErrorDecoder;
import feign.httpclient.ApacheHttpClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
public class ExtApiFeignConfig {

    @Bean
    public Feign.Builder feignBuilder(Gson gson) {
        return Feign.builder()
                .logLevel(logLevel())
                .errorDecoder(errorDecoder(gson));
    }

    @Bean
    public Client client() {
        final HttpClientBuilder builder = HttpClientBuilder.create()
                .setMaxConnPerRoute(100)
                .setMaxConnTotal(100);
        return new ApacheHttpClient(builder.build());
    }

    private Logger.Level logLevel() {
        return Logger.Level.FULL;
    }

    // Custom headers
    private List<Header> getHeaders() {
        return Lists.newArrayList(new BasicHeader("TEST-HEADER", "ExtApiFeignConfig"), new BasicHeader("TEST-HEADER2", "BLOG-SERVICE ECHO1"));
    }

    private ErrorDecoder errorDecoder(Gson gson) {
        return (methodKey, response) -> {
            ExtApiException.ErrorResponse errorResponse = null;
            try {
                final String body = IOUtils.toString(response.body().asInputStream(), StandardCharsets.UTF_8);
                log.error("ExtApiFeignConfig ErrorResponse : {}", body);
                errorResponse = gson.fromJson(body, ExtApiException.ErrorResponse.class);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
            return new ExtApiException(errorResponse, response.status());
        };
    }
}
