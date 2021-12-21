package com.example.demo.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;

@Component
public class JsonPlaceholderWebClient {

    private final HttpHeaders headers = new HttpHeaders();

    @Autowired
    WebClient client;

    private static final String allPosts = "/posts";
    private static final String singlePost = "/posts/%s";
    private static final String allTodos = "/todos";

    public static String BASE_URL = "http://jsonplaceholder.typicode.com";

    public Post getRequest(int postId) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.GET);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(String.format(JsonPlaceholderWebClient.BASE_URL + singlePost, postId));
        return Objects.requireNonNull(bodySpec.exchange().block(), "Response should be not null")
                .bodyToMono(Post.class).block();
    }

    public List<Post> getRequest() {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.GET);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(JsonPlaceholderWebClient.BASE_URL + allPosts);
        return Objects.requireNonNull(bodySpec.exchange().block(), "Response should be not null")
                .bodyToMono(new ParameterizedTypeReference<List<Post>>() {
                }).block();
    }

    public List<Post> postRequest(Post post) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.POST);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(JsonPlaceholderWebClient.BASE_URL + allPosts);
        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec.bodyValue(post);

        return Objects.requireNonNull(headersSpec.exchange().block(), "Response should be not null")
                .bodyToMono(new ParameterizedTypeReference<List<Post>>() {
                }).block();
    }

    public Post editRequest(int postId, Post post) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.PUT);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(String.format(JsonPlaceholderWebClient.BASE_URL + singlePost, postId));
        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec.bodyValue(post);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return Objects.requireNonNull(headersSpec.exchange().block(), "Response should be not null")
                .bodyToMono(Post.class).block();
    }

    public Post deleteRequest(int postId) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.DELETE);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(String.format(JsonPlaceholderWebClient.BASE_URL + singlePost, postId));
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return Objects.requireNonNull(bodySpec.exchange().block(), "Response should be not null")
                .bodyToMono(Post.class).block();
    }
}
