package com.example.demo.smokeSpecSuite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.testng.annotations.BeforeClass;

import static org.testng.AssertJUnit.assertNotNull;

public class BaseSpec {
    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    WebClient client;

    @BeforeClass
    public void setup() {
        assertNotNull(restTemplate);
        WebClient.builder();
    }
}
