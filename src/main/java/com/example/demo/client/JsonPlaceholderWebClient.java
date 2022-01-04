package com.example.demo.client;

import com.example.demo.projectVeriables.ProjectVeriables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Component
public class JsonPlaceholderWebClient {
    private static final Logger logger = Logger.getLogger(JsonPlaceholderWebClient.class.getName());

    @Autowired
    WebClient webClient;

    public JsonPlaceholderWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Post getPost(int id) {
        ClientResponse clientResponse = sendGet(String.format("/posts/%d", id), null, null);
        return clientResponse.bodyToMono(Post.class).block();
    }

    public List<Post> createPost(Post post) throws URISyntaxException {
        ClientResponse clientResponse = sendPost("/posts", null, post);
        return clientResponse.bodyToMono(new ParameterizedTypeReference<List<Post>>() {
        }).block();
    }

    public Post deletePost(int id) {
        ClientResponse clientResponse = sendDelete(String.format("/posts/%d", id), null);
        return clientResponse.bodyToMono(Post.class).block();
    }

    public Post patchPost(int id, Post post) {
        ClientResponse clientResponse = sendPatch(String.format("/posts/%d", id), post);
        return clientResponse.bodyToMono(Post.class).block();
    }

    public Post putPost(int id, Post post) {
        ClientResponse clientResponse = sendPut(String.format("/posts/%d", id), null, post);
        return clientResponse.bodyToMono(Post.class).block();
    }

    public List<Post> getAllPosts() {
        ClientResponse clientResponse = sendGet("/posts", null, null);
        return clientResponse.bodyToMono(new ParameterizedTypeReference<List<Post>>() {
        }).block();
    }

    public Todo getTodo(int id) {
        ClientResponse clientResponse = sendGet(String.format("/todos/%d", id), null, null);
        return clientResponse.bodyToMono(Todo.class).block();
    }

    public Todo postTodo(Todo todo) {
        ClientResponse clientResponse = sendPost("/posts", null, todo);
        return clientResponse.bodyToMono(Todo.class).block();
    }

    public Todo deleteTodo(int id) {
        ClientResponse clientResponse = sendDelete(String.format("/posts/%d", id), null);
        return clientResponse.bodyToMono(Todo.class).block();
    }

    public Todo patchTodo(int id, Todo todo) {
        ClientResponse clientResponse = sendPatch(String.format("/posts/%d", id), todo);
        return clientResponse.bodyToMono(Todo.class).block();
    }

    public Todo putTodo(int id, Todo todo) {
        ClientResponse clientResponse = sendPut(String.format("/posts/%d", id), null, todo);
        return clientResponse.bodyToMono(Todo.class).block();
    }

    public List<Todo> getAllTodos() {
        ClientResponse clientResponse = sendGet("/todos", null, null);
        return clientResponse.bodyToMono(new ParameterizedTypeReference<List<Todo>>() {
        }).block();
    }

    public ClientResponse sendGet(String url, HttpHeaders headers, MultiValueMap<String, String> myHash) {
        return this.webClient.get().uri(url,(uriBuilder) -> uriBuilder.queryParams(myHash).build()).headers((httpHeaders) ->{
            httpHeaders.addAll(headers);
        }).exchange().block();
    }

    public ClientResponse sendPost(String url, HttpHeaders headers, Object body) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = webClient.method(HttpMethod.POST);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(ProjectVeriables.BASE_URL + url);
        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec.bodyValue(body);
        logger.info("Sending POST request to : " + url + " and creating new element");
        return Objects.requireNonNull(headersSpec.exchange().block(), "Response should be not null");
    }

    public ClientResponse sendDelete(String url, HttpHeaders headers) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = webClient.method(HttpMethod.DELETE);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(ProjectVeriables.BASE_URL + url);
        logger.info("Sending DELETE request to : " + url + " and deleting element");
        return Objects.requireNonNull(bodySpec.exchange().block(), "Response should be not null");
    }

    public ClientResponse sendPatch(String url, Object body) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = webClient.method(HttpMethod.PATCH);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(ProjectVeriables.BASE_URL + url);
        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec.bodyValue(body);
        return Objects.requireNonNull(headersSpec.exchange().block(), "Response should be not null");
    }

    public ClientResponse sendPut(String url, HttpHeaders headers, Object body) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = webClient.method(HttpMethod.PUT);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(ProjectVeriables.BASE_URL + url);
        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec.bodyValue(body);
        logger.info("Sending PUT request to : " + url + " and changing element");
        return Objects.requireNonNull(headersSpec.exchange().block(), "Response should be not null");
    }
}
