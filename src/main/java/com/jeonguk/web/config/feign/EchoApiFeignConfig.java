package com.jeonguk.web.config.feign;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.jeonguk.web.config.feign.exception.EchoApiException;
import feign.Client;
import feign.Feign;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.httpclient.ApacheHttpClient;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class EchoApiFeignConfig {

    private final Gson gson;

    @Bean
    public Feign.Builder feignBuilder() {
        return Feign.builder()
            .client(client())
            .errorDecoder(errorDecoder())
            .decoder(decoder())
            .encoder(encoder());
    }

    private Client client() {
        final HttpClientBuilder builder = HttpClientBuilder.create()
            .setMaxConnPerRoute(100)
            .setMaxConnTotal(100)
            .setDefaultHeaders(getHeaders());
        return new ApacheHttpClient(builder.build());
    }

    // Custom headers
    private List<Header> getHeaders() {
        return Lists.newArrayList(new BasicHeader("TEST-HEADER", "BLOG-SERVICE ECHO1"), new BasicHeader("TEST-HEADER2", "BLOG-SERVICE ECHO1"));
    }

    private Decoder decoder() {
        return new GsonDecoder(gson);
    }

    private Encoder encoder() {
        return new GsonEncoder(gson);
    }

    private ErrorDecoder errorDecoder() {
        return (methodKey, response) -> {
            EchoApiException.ErrorResponse errorResponse = null;
            try {
                final String body = IOUtils.toString(response.body().asInputStream(), StandardCharsets.UTF_8.name());
                log.error("EchoApiFeignConfig ErrorResponse : {}", body);
                errorResponse = gson.fromJson(body, EchoApiException.ErrorResponse.class);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
            return new EchoApiException(errorResponse, response.status());
        };
    }

}
