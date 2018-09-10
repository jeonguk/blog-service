package com.jeonguk.web.config;


import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RestTemplateConfig.class, HttpClientConfig.class })
public class RestTemplateTestApplication {

    @Autowired
    RestTemplate restTemplate;

    @Test
    public void getPosts() {
        final String uri = "http://localhost:8088/echo";

        String result = restTemplate.getForObject(uri, String.class);
        log.info("result {}", result);
        Assert.assertEquals(true, result.indexOf("title") > 0);
    }

}
