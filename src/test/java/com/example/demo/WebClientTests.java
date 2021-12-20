package com.example.demo;

import com.example.demo.client.JsonPlaceholderWebClient;
import com.example.demo.client.PostObject;
import com.example.demo.smokeSpecSuite.BaseSpec;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

@SpringBootTest(classes = WebClientTests.class)
public class WebClientTests extends BaseSpec {

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    void getFirstPost_FirstPostInfoCorrect() {
        ResponseEntity<String> response
                = restTemplate.getForEntity(JsonPlaceholderWebClient.BASE_URL + "/posts/1", String.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    private void getFirstFromTodoList_getSuccessfullAndInfoCorrect() {
        ResponseEntity<String> result = JsonPlaceholderWebClient.getRequest("/todos/1");
        Assert.assertEquals(result.getStatusCodeValue(), 200);
    }

    @Test
    private void createNewPost_newPostSuccessfullyCreated() {
        PostObject postObject = new PostObject(12, 12, "TEST TITLE", true);

        ResponseEntity<String> result = JsonPlaceholderWebClient.postRequest("/todos", postObject);

        Assert.assertEquals(result.getStatusCodeValue(), 201);
    }

    @Test
    private void editExistingTodo_TodoEdited() {
        PostObject postObject = new PostObject(12, 12, "TEST TITLE", true);

        ResponseEntity<String> result = JsonPlaceholderWebClient.editRequest("/todos/1", postObject);

        Assert.assertEquals(result.getStatusCodeValue(), 200);
    }

    @Test
    private void deleteExistingTodo_TodoDeleted() {
        PostObject postObject = new PostObject(12, 12, "TEST TITLE", true);

        ResponseEntity<String> result = JsonPlaceholderWebClient.deleteRequest("/todos/1", postObject);

        Assert.assertEquals(result.getStatusCodeValue(), 200);
    }
}
