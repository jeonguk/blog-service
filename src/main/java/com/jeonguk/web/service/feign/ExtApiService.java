package com.jeonguk.web.service.feign;

import com.jeonguk.web.config.feign.ExtApiFeignConfig;
import com.jeonguk.web.dto.PostDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@FeignClient(name = "ext-feign-client", url = "${echo.api.server.url}", configuration = ExtApiFeignConfig.class)
public interface ExtApiService {

    @GetMapping("/api/echo/one")
    List<PostDTO.ResPost> getPostList();

}
