package com.jeonguk.web.controller;

import com.jeonguk.web.config.annotation.ApiVersion;
import com.jeonguk.web.dto.PostDTO;
import com.jeonguk.web.service.feign.EchoService;
import com.jeonguk.web.service.feign.ExtApiService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Call http://localhost:8080/api/v1/echo
 */
@AllArgsConstructor
@RestController
@RequestMapping("/echo")
@ApiVersion
public class EchoApiController {

    private final EchoService echoService;
    private final ExtApiService extApiService;

    @GetMapping("/one")
    List<PostDTO.ResPost> getPostListOne() {
        return echoService.getPostList();
    }

    @GetMapping("/two")
    List<PostDTO.ResPost> getPostListTwo() {
        return extApiService.getPostList();
    }

    @GetMapping("/with-header")
    List<PostDTO.ResPost> getPostListWighHeader() {
        final Map<String, String> header = new HashMap<>();
        header.put("TEST-HEADER", "BLOG-SERVICE");
        return echoService.getPostListWighHeader(header);
    }
}
