package com.jeonguk.web.service.feign;

import com.jeonguk.web.config.feign.EchoApiFeignConfig;
import com.jeonguk.web.dto.PostDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@FeignClient(name = "echo", url = "${echo.api.server.url}", configuration = EchoApiFeignConfig.class)
public interface EchoService {

    @GetMapping("/api/echo")
    List<PostDTO.ResPost> getPostList();

}
