package com.example.demo.smokeSpecSuite;

import com.example.demo.client.JsonPlaceholderWebClient;
import com.example.demo.client.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ToDoTest extends BaseSpec {

    @Autowired
    private JsonPlaceholderWebClient client;


    @Test
    private void testEditToDoById() {
        Post post = new Post(0, 12, "TEST TITLE", "TEST BODY");
        Post response = client.editRequest(1, post);
        Assert.assertEquals((int) response.getUserId(), 0,"id of the first post does not match");
    }

    @Test

    private void testDeleteToDoById() {
        Post response = client.deleteRequest(1);
        Assert.assertNull(response.getId(), "Post is not deleted");
    }
}
