package com.example.demo.smokeSpecSuite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest

public class BaseTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebClient client;
}
