package com.example.demo.smokeSpecSuite;

import com.example.demo.client.JsonPlaceholderTodoWebClient;
import com.example.demo.client.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class ToDoTest extends BaseTest {
    private static final String TITLE = "delectus aut autem";

    @Autowired
    private JsonPlaceholderTodoWebClient client;

    @Test
    private void testGetAllTodos() {
        List<Todo> allTodos = client.getAllTodos();
        System.out.println("ALL ToDos" + Arrays.toString(allTodos.toArray()));
        Assert.assertFalse(allTodos.isEmpty(), "allTodos arrays is empty! ");
    }

    @Test
    void testGetTodoById() {
        Todo response = client.getTodo(1);
        Assert.assertEquals((int) response.getId(), 1, "id of the first todo does not match");
        Assert.assertEquals(response.getTitle(), TITLE, "Title does not match");
    }

    @Test
    private void testCreateNewTodo() {
        Todo newTodoItem = new Todo(9999, 12, "TEST TITLE", true);
        Todo response = client.postTodo(newTodoItem);
        Assert.assertEquals((int) response.getUser_Id(), 9999, "userId of the new created Todo does not match");
        Assert.assertEquals(response.getTitle(), "TEST TITLE", "title of the new created Todo does not match");
    }

    @Test
    private void testEditTodoById() {
        Todo newTodoItem = new Todo(9999, 12, "TEST TITLE", true);

        Todo response = client.putTodo(1, newTodoItem);
        Assert.assertEquals((int) response.getUser_Id(), 9999, "id of the first todo does not match");
    }

    @Test
    private void testDeletePostById() {
        Todo response = client.deleteTodo(1);
        Assert.assertNull(response.getId(), "Todo is not deleted");
    }
}
