package com.example.demo.client;

import com.example.demo.enumeration.EndPointEnum;
import com.example.demo.enumeration.MethodTypeEnum;
import com.example.demo.projectVeriables.ProjectVeriables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Component
public class JsonPlaceholderWebClient {
    private static final Logger logger = Logger.getLogger(JsonPlaceholderWebClient.class.getName());

    private final HttpHeaders headers = new HttpHeaders();

    @Autowired
    WebClient client;

    public Post sendGet(EndPointEnum url, MethodTypeEnum query,HttpHeaders headers ) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.GET);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(ProjectVeriables.BASE_URL + url);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return Objects.requireNonNull(bodySpec.exchange().block(), "Response should be not null")
                .bodyToMono(Post.class).block();
    }
    public Post sendPost(EndPointEnum url, MethodTypeEnum query,HttpHeaders headers, Post post) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.POST);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(ProjectVeriables.BASE_URL + url);
        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec.bodyValue(post);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return Objects.requireNonNull(headersSpec.exchange().block(), "Response should be not null")
                .bodyToMono(Post.class).block();
    }
    public Post sendDelete(EndPointEnum url, MethodTypeEnum query,HttpHeaders headers) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.DELETE);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(ProjectVeriables.BASE_URL + url);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return Objects.requireNonNull(bodySpec.exchange().block(), "Response should be not null")
                .bodyToMono(Post.class).block();
    }
    public Post sendPatch(EndPointEnum url, MethodTypeEnum query,HttpHeaders headers, Post post) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.PATCH);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(ProjectVeriables.BASE_URL + url);
        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec.bodyValue(post);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return Objects.requireNonNull(headersSpec.exchange().block(), "Response should be not null")
                .bodyToMono(Post.class).block();
    }
    public Post sendPut(EndPointEnum url, MethodTypeEnum query,HttpHeaders headers) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.PUT);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(ProjectVeriables.BASE_URL + url);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return Objects.requireNonNull(bodySpec.exchange().block(), "Response should be not null")
                .bodyToMono(Post.class).block();
    }
}
