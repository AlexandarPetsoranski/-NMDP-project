package com.example.demo.client;

import com.example.demo.enumeration.MethodTypeEnum;
import com.example.demo.projectVeriables.ProjectVeriables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Component
public class JsonPlaceholderWebClient {
    private static final Logger logger = Logger.getLogger(JsonPlaceholderWebClient.class.getName());

    @Autowired
    WebClient client;

    public ClientResponse sendGet(String url, MethodTypeEnum query, HttpHeaders headers) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.GET);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(ProjectVeriables.BASE_URL + url);
        logger.info("Sending GET request to : " + url + "and getting element");
        return Objects.requireNonNull(bodySpec.exchange().block(), "Response should be not null");
    }

    public ClientResponse sendPost(String url, MethodTypeEnum query, HttpHeaders headers, Object body) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.POST);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(ProjectVeriables.BASE_URL + url);
        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec.bodyValue(body);
        logger.info("Sending POST request to : " + url + " and creating new element");

        return Objects.requireNonNull(headersSpec.exchange().block(), "Response should be not null");
    }

    public ClientResponse sendDelete(String url, MethodTypeEnum query, HttpHeaders headers) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.DELETE);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(ProjectVeriables.BASE_URL + url);
        logger.info("Sending DELETE request to : " + url + " and deleting element");
        return Objects.requireNonNull(bodySpec.exchange().block(), "Response should be not null");
    }

    public ClientResponse sendPatch(String url, MethodTypeEnum query, HttpHeaders headers, Object body) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.PATCH);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(ProjectVeriables.BASE_URL + url);
        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec.bodyValue(body);
        return Objects.requireNonNull(headersSpec.exchange().block(), "Response should be not null");
    }

    public ClientResponse sendPut(String url, MethodTypeEnum query, HttpHeaders headers, Object body) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.PUT);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(ProjectVeriables.BASE_URL + url);
        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec.bodyValue(body);
        logger.info("Sending PUT request to : " + url  + " and changing element");
        return Objects.requireNonNull(headersSpec.exchange().block(), "Response should be not null");
    }

    public Post getPost() {
        ClientResponse clientResponse = sendGet("/posts/1", null, null);
        return clientResponse.bodyToMono(Post.class).block();
    }

    public List<Post> postPost() {
        Post newPostElement = new Post(9999, 12, "TEST TITLE", "TEST BODY");
        ClientResponse clientResponse = sendPost("/posts/1", null, null, newPostElement);
        return clientResponse.bodyToMono(new ParameterizedTypeReference<List<Post>>() {
        }).block();
    }

    public Post deletePost() {
        ClientResponse clientResponse = sendDelete("/posts/1", null, null);
        return clientResponse.bodyToMono(Post.class).block();
    }

    public Post patchPost() {
        Post newPostElement = new Post(9999, 12, "TEST TITLE", "TEST BODY");

        ClientResponse clientResponse = sendPatch("/posts/1", null, null, newPostElement);
        return clientResponse.bodyToMono(Post.class).block();
    }

    public Post putPost() {
        Post newPostElement = new Post(9999, 12, "TEST TITLE", "TEST BODY");

        ClientResponse clientResponse = sendPut("/posts/1", null, null, newPostElement);
        return clientResponse.bodyToMono(Post.class).block();
    }

    public List<Post> getAllPosts() {
        ClientResponse clientResponse = sendGet("/posts", null, null);
        return clientResponse.bodyToMono(new ParameterizedTypeReference<List<Post>>() {
        }).block();
    }

    public Todo getTodo() {
        ClientResponse clientResponse = sendGet("/todos/1", null, null);
        return clientResponse.bodyToMono(Todo.class).block();
    }

    public List<Todo> postTodo() {
        Todo newTodoItem = new Todo(9999, 12, "TEST TITLE", true);
        ClientResponse clientResponse = sendPost("/posts/1", null, null, newTodoItem);
        return clientResponse.bodyToMono(new ParameterizedTypeReference<List<Todo>>() {
        }).block();
    }

    public Todo deleteTodo() {
        ClientResponse clientResponse = sendDelete("/posts/1", null, null);
        return clientResponse.bodyToMono(Todo.class).block();
    }

    public Todo patchTodo() {
        Todo newTodoItem = new Todo(9999, 12, "TEST TITLE", true);

        ClientResponse clientResponse = sendPatch("/posts/1", null, null, newTodoItem);
        return clientResponse.bodyToMono(Todo.class).block();
    }

    public Todo putTodo() {
        Todo newTodoItem = new Todo(9999, 12, "TEST TITLE", true);

        ClientResponse clientResponse = sendPut("/posts/1", null, null, newTodoItem);
        return clientResponse.bodyToMono(Todo.class).block();
    }

    public List<Todo> getAllTodos() {
        ClientResponse clientResponse = sendGet("/todos", null, null);
        return clientResponse.bodyToMono(new ParameterizedTypeReference<List<Todo>>() {
        }).block();
    }
}
