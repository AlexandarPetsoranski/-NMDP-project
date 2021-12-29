package com.example.demo.client;

import com.example.demo.projectVeriables.ProjectVeriables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

@Component
public class JsonPlaceholderWebClient {
    private static final Logger logger = Logger.getLogger(JsonPlaceholderWebClient.class.getName());

    @Autowired
    WebClient client;

    public Post getPost(int id) {
        ClientResponse clientResponse = sendGet(String.format("/posts/%d", id), null, null,null);
        return clientResponse.bodyToMono(Post.class).block();
    }

    public <T> List<T> createPost() {
        Post newPostElement = new Post(9999, 12, "TEST TITLE", "TEST BODY");
        ClientResponse clientResponse = sendPost("/posts", null, null, newPostElement);
        return clientResponse.bodyToMono(new ParameterizedTypeReference<List<T>>() {
        }).block();
    }

    public Post deletePost(int id) {
        ClientResponse clientResponse = sendDelete(String.format("/posts/%d", id), null, null);
        return clientResponse.bodyToMono(Post.class).block();
    }

    public Post patchPost(int id) {
        Post newPostElement = new Post(9999, 12, "TEST TITLE", "TEST BODY");

        ClientResponse clientResponse = sendPatch(String.format("/posts/%d", id), null, null, newPostElement);
        return clientResponse.bodyToMono(Post.class).block();
    }

    public Post putPost(int id) {
        Post newPostElement = new Post(9999, 12, "TEST TITLE", "TEST BODY");

        ClientResponse clientResponse = sendPut(String.format("/posts/%d", id), null, null, newPostElement);
        return clientResponse.bodyToMono(Post.class).block();
    }

    public List<Post> getAllPosts() {
        ClientResponse clientResponse = sendGet("/posts", null, null,null);
        return clientResponse.bodyToMono(new ParameterizedTypeReference<List<Post>>() {
        }).block();
    }

    public Todo getTodo(int id) {
        ClientResponse clientResponse = sendGet(String.format("/posts/%d", id), null, null,null);
        return clientResponse.bodyToMono(Todo.class).block();
    }

    public <T> List<T> postTodo(int id) {
        Todo newTodoItem = new Todo(9999, 12, "TEST TITLE", true);
        ClientResponse clientResponse = sendPost(String.format("/posts/%d", id), null, null, newTodoItem);
        return clientResponse.bodyToMono(new ParameterizedTypeReference<List<T>>() {
        }).block();
    }

    public Todo deleteTodo(int id) {
        ClientResponse clientResponse = sendDelete(String.format("/posts/%d", id), null, null);
        return clientResponse.bodyToMono(Todo.class).block();
    }

    public Todo patchTodo(int id) {
        Todo newTodoItem = new Todo(9999, 12, "TEST TITLE", true);

        ClientResponse clientResponse = sendPatch(String.format("/posts/%d", id), null, null, newTodoItem);
        return clientResponse.bodyToMono(Todo.class).block();
    }

    public Todo putTodo(int id) {
        Todo newTodoItem = new Todo(9999, 12, "TEST TITLE", true);

        ClientResponse clientResponse = sendPut(String.format("/posts/%d", id), null, null, newTodoItem);
        return clientResponse.bodyToMono(Todo.class).block();
    }

    public List<Todo> getAllTodos() {
        ClientResponse clientResponse = sendGet("/todos", null, null,null);
        return clientResponse.bodyToMono(new ParameterizedTypeReference<List<Todo>>() {
        }).block();
    }

    public ClientResponse sendGet(String url, RequestMethod query, HttpHeaders headers, HashMap<String, String> myHash) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.GET);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(ProjectVeriables.BASE_URL + url);
        logger.info("Sending GET request to : " + url + "and getting element");
        return Objects.requireNonNull(bodySpec.exchange().block(), "Response should be not null");
    }

    public ClientResponse sendPost(String url, RequestMethod query, HttpHeaders headers, Object body) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.POST);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(ProjectVeriables.BASE_URL + url);
        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec.bodyValue(body);
        logger.info("Sending POST request to : " + url + " and creating new element");

        return Objects.requireNonNull(headersSpec.exchange().block(), "Response should be not null");
    }

    public ClientResponse sendDelete(String url, RequestMethod query, HttpHeaders headers) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.DELETE);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(ProjectVeriables.BASE_URL + url);
        logger.info("Sending DELETE request to : " + url + " and deleting element");
        return Objects.requireNonNull(bodySpec.exchange().block(), "Response should be not null");
    }

    public ClientResponse sendPatch(String url, RequestMethod query, HttpHeaders headers, Object body) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.PATCH);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(ProjectVeriables.BASE_URL + url);
        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec.bodyValue(body);
        return Objects.requireNonNull(headersSpec.exchange().block(), "Response should be not null");
    }

    public ClientResponse sendPut(String url, RequestMethod query, HttpHeaders headers, Object body) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.PUT);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(ProjectVeriables.BASE_URL + url);
        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec.bodyValue(body);
        logger.info("Sending PUT request to : " + url + " and changing element");
        return Objects.requireNonNull(headersSpec.exchange().block(), "Response should be not null");
    }
}
