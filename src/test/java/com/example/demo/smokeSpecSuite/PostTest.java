package com.example.demo.smokeSpecSuite;

import com.example.demo.client.JsonPlaceholderWebClient;
import com.example.demo.client.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class PostTest extends BaseSpec{
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private JsonPlaceholderWebClient client;

    @Test
    private void testGetAllPosts(){
        List<Post> allPosts = client.getRequest();
        System.out.println("ALL POSTS" + Arrays.toString(allPosts.toArray()));
        Assert.assertFalse(allPosts.isEmpty(),"allPost arrays is empty! ");
    }


    @Test
    void testGetPostById() {
        Post response = client.getRequest(1);
        Assert.assertEquals((int) response.getId(), 1,"id of the first post does not match");
        Assert.assertTrue(response.getTitle().contains("sunt aut facere"),"Title does not match");
    }

    @Test
    private void testCreateNewPost() {
        Post post = new Post(9999, 12, "TEST TITLE", "TEST BODY");
        List<Post> listOfPosts = client.postRequest(post);
        Assert.assertEquals((int) listOfPosts.get(1).getId(), 9999,"id of the first post does not match");
    }
}
