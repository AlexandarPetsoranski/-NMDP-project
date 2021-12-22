package com.example.demo.smokeSpecSuite;

import com.example.demo.client.JsonPlaceholderWebClient;
import com.example.demo.client.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class ToDoTest extends BaseTest {
    private static final String TITLE = "delectus aut autem";

    @Autowired
    private JsonPlaceholderWebClient client;

    @Test
    private void testGetAllTodos() {
        List<Todo> allTodos = client.getAllTodos();
        System.out.println("ALL POSTS" + Arrays.toString(allTodos.toArray()));
        Assert.assertFalse(allTodos.isEmpty(), "allPost arrays is empty! ");
    }

    @Test
    void testGetTodoById() {
        Todo response = client.getTodo();
        Assert.assertEquals((int) response.getId(), 1, "id of the first post does not match");
        Assert.assertEquals(response.getTitle(), TITLE, "Title does not match");
    }
    @Test
    private void testCreateNewTodo() {
        List<Todo> response = client.postTodo();
        Assert.assertEquals((int) response.get(1).getId(), 9999, "id of the first post does not match");
    }

    @Test
    private void testEditTodoById() {
        Todo response = client.putTodo();
        Assert.assertEquals((int) response.getUser_Id(), 9999, "id of the first post does not match");
    }

    @Test
    private void testDeletePostById() {
        Todo response = client.deleteTodo();
        Assert.assertNull(response.getId(), "Post is not deleted");
    }
}
