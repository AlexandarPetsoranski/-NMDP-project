package com.example.demo.client;

import com.example.demo.client.base.BaseClientImpl;
import com.example.demo.projectVeriables.ProjectVeriables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.ClientResponse;

import java.util.List;

@Component
public class JsonPlaceholderTodoWebClient {

    private final HttpHeaders httpHeaders = new HttpHeaders();

    @Autowired
    BaseClientImpl baseClientImpl;

    public Todo getTodo(int id) {
        MultiValueMap queryParams = new LinkedMultiValueMap();
        ClientResponse clientResponse = baseClientImpl.sendGet(String.format(ProjectVeriables.BASE_URL + "/todos/%d", id), httpHeaders, queryParams);
        return clientResponse.bodyToMono(Todo.class).block();
    }

    public Todo postTodo(Todo todo) {
        MultiValueMap queryParams = new LinkedMultiValueMap();
        ClientResponse clientResponse = baseClientImpl.sendPost(ProjectVeriables.BASE_URL + "/todos", httpHeaders, queryParams, todo, Todo.class);
        return clientResponse.bodyToMono(Todo.class).block();
    }

    public Todo deleteTodo(int id) {
        MultiValueMap queryParams = new LinkedMultiValueMap();
        ClientResponse clientResponse = baseClientImpl.sendDelete(String.format(ProjectVeriables.BASE_URL + "/todos/%d", id), httpHeaders, queryParams);
        return clientResponse.bodyToMono(Todo.class).block();
    }

    public Todo patchTodo(int id, Todo todo) {
        MultiValueMap queryParams = new LinkedMultiValueMap();
        ClientResponse clientResponse = baseClientImpl.sendPatch(String.format(ProjectVeriables.BASE_URL + "/todos/%d", id), httpHeaders, queryParams, todo, Todo.class);
        return clientResponse.bodyToMono(Todo.class).block();
    }

    public Todo putTodo(int id, Todo todo) {
        MultiValueMap queryParams = new LinkedMultiValueMap();
        ClientResponse clientResponse = baseClientImpl.sendPut(String.format(ProjectVeriables.BASE_URL + "/todos/%d", id), httpHeaders, queryParams, todo, Todo.class);
        return clientResponse.bodyToMono(Todo.class).block();
    }

    public List<Todo> getAllTodos() {
        MultiValueMap queryParams = new LinkedMultiValueMap();
        ClientResponse clientResponse = baseClientImpl.sendGet(ProjectVeriables.BASE_URL + "/todos", httpHeaders, queryParams);
        return clientResponse.bodyToMono(new ParameterizedTypeReference<List<Todo>>() {
        }).block();
    }
}
