package com.example.demo.smokeSpecSuite;

import com.example.demo.client.JsonPlaceholderWebClient;
import com.example.demo.client.Post;
import com.example.demo.enumeration.EndPointEnum;
import com.example.demo.enumeration.MethodTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class PostTest extends BaseTest {

    private static final String TITLE = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit";
    @Autowired
    private JsonPlaceholderWebClient client;

    @Test
    private void testGetAllPosts() {
        List<Object> allPosts = client.sendRequestToAll(MethodTypeEnum.GET, EndPointEnum.ALL_POSTS, null);
        System.out.println("ALL POSTS" + Arrays.toString(allPosts.toArray()));
        Assert.assertFalse(allPosts.isEmpty(), "allPost arrays is empty! ");
    }

    @Test
    void testGetPostById() {
        Object response = client.sendRequestWithID(MethodTypeEnum.GET, EndPointEnum.SINGLE_POST, 1, null);
        Post newPostElement = (Post) response;
        Assert.assertEquals((int) newPostElement.getId(), 1, "id of the first post does not match");
        Assert.assertEquals(newPostElement.getTitle(), TITLE, "Title does not match");
    }

    @Test
    private void testCreateNewPost() {
        Post newPostElement = new Post(9999, 12, "TEST TITLE", "TEST BODY");
        List<Object> listOfPosts = client.sendRequestToAll(MethodTypeEnum.POST, EndPointEnum.ALL_POSTS, newPostElement);
        List<Post> allPosts = (List<Post>)(Object) listOfPosts;

        Assert.assertEquals((int) allPosts.get(1).getId(), 9999, "id of the first post does not match");
    }

    @Test
    private void testEditPostById() {
        Post newPostElement = new Post(0, 12, "TEST TITLE", "TEST BODY");
        Object response = client.sendRequestWithID(MethodTypeEnum.PUT, EndPointEnum.SINGLE_POST, 1, newPostElement);
        newPostElement = (Post) response;

        Assert.assertEquals((int) newPostElement.getUserId(), 0, "id of the first post does not match");
    }

    @Test
    private void testDeletePostById() {
        Object response = client.sendRequestWithID(MethodTypeEnum.DELETE, EndPointEnum.SINGLE_POST, 1, null);
        Post newPostElement = (Post) response;
        Assert.assertNull(newPostElement.getId(), "Post is not deleted");
    }
}
