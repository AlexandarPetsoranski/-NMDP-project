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

public class ToDoTest extends BaseSpec{
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private JsonPlaceholderWebClient client;

    @Test
    private void getFirstFromTodoList_getSuccessfullAndInfoCorrect() {
        Mono<String> result = client.getRequest("/todos/1");
        Assert.assertEquals(client.getStatusCode(), HttpStatus.OK, "Status code is not as expected");
    }

    @Test
    private void editExistingTodo_TodoEdited() {
        Post post = new Post(12, 12, "TEST TITLE", true);

        Mono<String> result = client.editRequest("/todos/1", post);

        Assert.assertEquals(result.getStatusCode(), HttpStatus.OK, "Status code is not as expected");
    }

    @Test
    private void deleteExistingTodo_TodoDeleted() {
        Post post = new Post(12, 12, "TEST TITLE", true);

        Mono<String> result = client.deleteRequest("/todos/1");

        Assert.assertEquals(result.getStatusCode(), HttpStatus.OK, "Status code is not as expected");
    }
}
