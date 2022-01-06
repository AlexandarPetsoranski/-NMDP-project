package com.example.demo.smokeSpecSuite;

import com.example.demo.client.BinWebClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

@Log4j2

public class BinTests extends BaseTest {

    @Autowired
    BinWebClient binWebClient;


    @Test
    private void sendAuthenticationGetCall() {
        ClientResponse response = binWebClient.httpBasicAuth("admin", "admin");
        Assert.assertTrue(response.statusCode().is2xxSuccessful());
    }
}
