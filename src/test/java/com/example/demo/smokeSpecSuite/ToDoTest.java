package com.example.demo.smokeSpecSuite;

import com.example.demo.client.JsonPlaceholderWebClient;
import com.example.demo.client.Todo;
import com.example.demo.enumeration.EndPointEnum;
import com.example.demo.enumeration.MethodTypeEnum;
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
    private void testGetAllTodos(){
        List<Object> allTodos = client.sendRequestToAll(MethodTypeEnum.GET, EndPointEnum.ALL_TODOS,null);
        System.out.println("ALL Todos are " + Arrays.toString(allTodos.toArray()));
        Assert.assertFalse(allTodos.isEmpty(),"allTodos arrays is empty! ");
    }

    @Test
    void testGetTodosById() {
        Object response = client.sendRequestWithID(MethodTypeEnum.GET,EndPointEnum.SINGLE_TODO,1,null);
        Todo newTodoElement = (Todo) response;
        Assert.assertEquals((int) newTodoElement.getId(), 1,"id of the first todo does not match");
        Assert.assertEquals(newTodoElement.getTitle(),TITLE,"Title does not match");
    }

    @Test
    private void testCreateNewTodo() {
        Todo newTodoItem = new Todo(9999, 12, "TEST TITLE", true);
        List<Object> listOfTodos = client.sendRequestToAll(MethodTypeEnum.POST,EndPointEnum.ALL_TODOS,null);
        List<Todo> allTodos = (List<Todo>)(Object) listOfTodos;

        Assert.assertEquals((int) allTodos.get(1).getId(), 9999,"id of the first todo does not match");
    }

    @Test
    private void testEditToDoById() {
        Todo newTodoItem = new Todo(0, 12, "TEST TITLE", true);
        Object response = client.sendRequestWithID(MethodTypeEnum.PUT,EndPointEnum.SINGLE_TODO,1,null);
        newTodoItem = (Todo) response;

        Assert.assertEquals((int) newTodoItem.getUser_Id(), 0,"id of the first todo does not match");
    }

    @Test
    private void testDeleteToDoById() {
        Object response = client.sendRequestWithID(MethodTypeEnum.DELETE,EndPointEnum.SINGLE_TODO,1,null);
        Todo newTodoElement = (Todo) response;
        Assert.assertNull(newTodoElement.getId(), "Todo is not deleted");
    }

}
