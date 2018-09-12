package com.jeonguk.web.service.feign;

import com.jeonguk.web.config.feign.EchoApiFeignConfig;
import com.jeonguk.web.dto.PostDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Map;

@Service
@FeignClient(name = "echo-feign-client", url = "${echo.api.server.url}", configuration = EchoApiFeignConfig.class)
public interface EchoService {

    @GetMapping("/api/echo/one")
    List<PostDTO.ResPost> getPostList();

    @GetMapping("/api/echo/with-header")
    List<PostDTO.ResPost> getPostListWighHeader(@RequestHeader Map<String, String> header);

}
