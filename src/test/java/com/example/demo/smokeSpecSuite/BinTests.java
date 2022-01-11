package com.example.demo.smokeSpecSuite;

import com.example.demo.client.BinWebClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

@Log4j2

public class BinTests extends BaseTest {

    @Autowired
    BinWebClient binWebClient;

    @Test
    private void sendHttpAuth() {
        ClientResponse response = binWebClient.httpBasicAuth("admin", "admin");
        Assert.assertTrue(response.statusCode().is2xxSuccessful(), "Response code is not 200");
    }

    @Test
    private void sendBearerAuth(){
        ClientResponse response = binWebClient.bearerAuthentication("Test token");
        Assert.assertEquals(response.statusCode(), HttpStatus.OK, "Response code is not 401");
    }

    @Test
    private void sendDigestAuth() {
        ClientResponse response = binWebClient.digestAuthentication("3","3", "3");
        Assert.assertEquals(response.statusCode(), HttpStatus.UNAUTHORIZED, "Response code is not 200");
    }
}
