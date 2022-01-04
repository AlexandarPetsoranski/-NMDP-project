package com.example.demo.client;

import com.example.demo.projectVeriables.ProjectVeriables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class JsonPlaceholderWebClient {

    private final HttpHeaders httpHeaders = new HttpHeaders();

    @Autowired
    WebClient webClient;

    public JsonPlaceholderWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Post getPost(int id) {
        MultiValueMap queryParams = new LinkedMultiValueMap();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        ClientResponse clientResponse = sendGet(String.format(ProjectVeriables.BASE_URL + "/posts/%d", id), httpHeaders, queryParams);
        return clientResponse.bodyToMono(Post.class).block();
    }

    public Post createPost(Post post) {
        MultiValueMap queryParams = new LinkedMultiValueMap();
        ClientResponse clientResponse = sendPost(ProjectVeriables.BASE_URL + "/posts", httpHeaders, queryParams, post, Post.class);
        return clientResponse.bodyToMono(Post.class).block();
    }

    public Post deletePost(int id) {
        MultiValueMap queryParams = new LinkedMultiValueMap();
        ClientResponse clientResponse = sendDelete(String.format(ProjectVeriables.BASE_URL + "/posts/%d", id), httpHeaders, queryParams);
        return clientResponse.bodyToMono(Post.class).block();
    }

    public Post patchPost(int id, Post post) {
        MultiValueMap queryParams = new LinkedMultiValueMap();
        ClientResponse clientResponse = sendPatch(String.format(ProjectVeriables.BASE_URL + "/posts/%d", id), httpHeaders, queryParams, post, Post.class);
        return clientResponse.bodyToMono(Post.class).block();
    }

    public Post putPost(int id, Post post) {
        MultiValueMap queryParams = new LinkedMultiValueMap();
        ClientResponse clientResponse = sendPut(String.format(ProjectVeriables.BASE_URL + "/posts/%d", id), httpHeaders, queryParams, post, Post.class);
        return clientResponse.bodyToMono(Post.class).block();
    }

    public List<Post> getAllPosts() {
        MultiValueMap queryParams = new LinkedMultiValueMap();
        ClientResponse clientResponse = sendGet(ProjectVeriables.BASE_URL + "/posts", httpHeaders, queryParams);
        return clientResponse.bodyToMono(new ParameterizedTypeReference<List<Post>>() {
        }).block();
    }

    public Todo getTodo(int id) {
        MultiValueMap queryParams = new LinkedMultiValueMap();
        ClientResponse clientResponse = sendGet(String.format(ProjectVeriables.BASE_URL + "/todos/%d", id), httpHeaders, queryParams);
        return clientResponse.bodyToMono(Todo.class).block();
    }

    public Todo postTodo(Todo todo) {
        MultiValueMap queryParams = new LinkedMultiValueMap();
        ClientResponse clientResponse = sendPost(ProjectVeriables.BASE_URL + "/todos", httpHeaders, queryParams, todo, Todo.class);
        return clientResponse.bodyToMono(Todo.class).block();
    }

    public Todo deleteTodo(int id) {
        MultiValueMap queryParams = new LinkedMultiValueMap();
        ClientResponse clientResponse = sendDelete(String.format(ProjectVeriables.BASE_URL + "/todos/%d", id), httpHeaders, queryParams);
        return clientResponse.bodyToMono(Todo.class).block();
    }

    public Todo patchTodo(int id, Todo todo) {
        MultiValueMap queryParams = new LinkedMultiValueMap();
        ClientResponse clientResponse = sendPatch(String.format(ProjectVeriables.BASE_URL + "/todos/%d", id), httpHeaders, queryParams, todo, Todo.class);
        return clientResponse.bodyToMono(Todo.class).block();
    }

    public Todo putTodo(int id, Todo todo) {
        MultiValueMap queryParams = new LinkedMultiValueMap();
        ClientResponse clientResponse = sendPut(String.format(ProjectVeriables.BASE_URL + "/todos/%d", id), httpHeaders, queryParams, todo, Todo.class);
        return clientResponse.bodyToMono(Todo.class).block();
    }

    public List<Todo> getAllTodos() {
        MultiValueMap queryParams = new LinkedMultiValueMap();
        ClientResponse clientResponse = sendGet(ProjectVeriables.BASE_URL + "/todos", httpHeaders, queryParams);
        return clientResponse.bodyToMono(new ParameterizedTypeReference<List<Todo>>() {
        }).block();
    }

    public ClientResponse sendGet(String url, HttpHeaders headers, MultiValueMap<String, String> myHash) {
        return this.webClient.get().uri(url, (uriBuilder) -> uriBuilder.queryParams(myHash).build()).headers((httpHeaders) -> {
            httpHeaders.addAll(headers);
        }).exchange().block();
    }

    public ClientResponse sendPost(String url, HttpHeaders headers, MultiValueMap<String, String> myHash, Object body, Class<?> bodyType) {
        return this.webClient.post().uri(url, (uriBuilder) -> uriBuilder.queryParams(myHash).build()).body(BodyInserters.fromProducer(Mono.just(body), bodyType)).headers((httpHeaders) -> {
            httpHeaders.addAll(headers);
        }).exchange().block();
    }

    public ClientResponse sendDelete(String url, HttpHeaders headers, MultiValueMap<String, String> myHash) {
        return this.webClient.delete().uri(url, (uriBuilder) -> uriBuilder.queryParams(myHash).build()).headers((httpHeaders) -> {
            httpHeaders.addAll(headers);
        }).exchange().block();
    }

    public ClientResponse sendPatch(String url, HttpHeaders headers, MultiValueMap<String, String> myHash, Object body, Class<?> bodyType) {
        return this.webClient.patch().uri(url, (uriBuilder) -> uriBuilder.queryParams(myHash).build()).body(BodyInserters.fromProducer(Mono.just(body), bodyType)).headers((httpHeaders) -> {
            httpHeaders.addAll(headers);
        }).exchange().block();
    }

    public ClientResponse sendPut(String url, HttpHeaders headers, MultiValueMap<String, String> myHash, Object body, Class<?> bodyType) {
        return this.webClient.put().uri(url, (uriBuilder) -> uriBuilder.queryParams(myHash).build()).body(BodyInserters.fromProducer(Mono.just(body), bodyType)).headers((httpHeaders) -> {
            httpHeaders.addAll(headers);
        }).exchange().block();
    }
}
