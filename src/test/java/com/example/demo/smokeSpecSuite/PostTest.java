package com.example.demo.smokeSpecSuite;

import com.example.demo.client.JsonPlaceholderWebClient;
import com.example.demo.client.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;
import reactor.core.publisher.Mono;

public class PostTest extends BaseSpec{
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private JsonPlaceholderWebClient client;

    @Test
    void getFirstPost_FirstPostInfoCorrect() {
        ResponseEntity<String> response
                = restTemplate.getForEntity(JsonPlaceholderWebClient.BASE_URL + "/posts/1", String.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK, "Status code is not as expected");
    }

    @Test
    private void createNewPost_newPostSuccessfullyCreated() {
        Post post = new Post(12, 12, "TEST TITLE", true);

        Mono<String> result = client.postRequest("/todos", post);

        Assert.assertEquals(result.getStatusCode(), HttpStatus.OK, "Status code is not as expected");
    }
}
