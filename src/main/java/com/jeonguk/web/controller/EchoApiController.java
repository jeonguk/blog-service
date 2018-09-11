package com.jeonguk.web.controller;

import com.jeonguk.web.dto.PostDTO;
import com.jeonguk.web.service.feign.EchoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/echo")
public class EchoApiController {

    private final EchoService echoService;

    @GetMapping
    List<PostDTO.ResPost> getPostList() {
        return echoService.getPostList();
    }
}
