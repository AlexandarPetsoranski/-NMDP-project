package com.example.demo.smokeSpecSuite;

import com.example.demo.client.JsonPlaceholderWebClient;
import com.example.demo.client.Post;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
@Log4j2
public class PostTest extends BaseTest {

    private static final String TITLE = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit";
    @Autowired
    private JsonPlaceholderWebClient client;

    @Test
    private void testGetAllPosts() {
        List<Post> allPosts = client.getAllPosts();
        log.info("ALL POSTS" + Arrays.toString(allPosts.toArray()));
        Assert.assertFalse(allPosts.isEmpty(), "allPost arrays is empty! ");
    }

    @Test
    void testGetPostById() {
        Post response = client.getPost(1);
        Assert.assertEquals((int) response.getId(), 1, "id of the first post does not match");
        Assert.assertEquals(response.getTitle(), TITLE, "Title does not match");

    }

    @Test
    private void testCreateNewPost(){
        Post newPostElement = new Post(9999, 12, "TEST TITLE", "TEST BODY");
        Post response = client.createPost(newPostElement);
        Assert.assertEquals((int)response.getUserId(), 9999, "userId of the new created post does not match");
        Assert.assertEquals(response.getTitle(), "TEST TITLE", "title of the new created post does not match");
    }

    @Test
    private void testEditPostById() {
        Post newPostElement = new Post(9999, 12, "TEST TITLE", "TEST BODY");

        Post response = client.putPost(1, newPostElement);
        Assert.assertEquals((int) response.getUserId(), 9999, "id of the first post does not match");
    }

    @Test
    private void testDeletePostById() {
        Post response = client.deletePost(1);
        Assert.assertNull(response.getId(), "Post is not deleted");
    }
}
