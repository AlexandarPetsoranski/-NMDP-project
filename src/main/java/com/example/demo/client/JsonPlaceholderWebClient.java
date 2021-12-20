package com.example.demo.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.List;

@Component
public class JsonPlaceholderWebClient {

    private final HttpHeaders headers = new HttpHeaders();

    @Autowired
    WebClient client;

    public static String BASE_URL = "http://jsonplaceholder.typicode.com";

    public Mono<String> getRequest(String addEndPoint) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.GET);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(JsonPlaceholderWebClient.BASE_URL + addEndPoint);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        return bodySpec.exchangeToMono(response -> {
            if (response.statusCode()
                    .equals(HttpStatus.OK)) {
                return response.bodyToMono(String.class);
            } else if (response.statusCode()
                    .is4xxClientError()) {
                return Mono.just("Error response");
            } else {
                return response.createException()
                        .flatMap(Mono::error);
            }
        });
    }

    public Mono<String> postRequest(String addEndPoint, Post post) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.POST);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(JsonPlaceholderWebClient.BASE_URL + addEndPoint);
        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec.bodyValue(post);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        return headersSpec.exchangeToMono(response -> {
            if (response.statusCode()
                    .equals(HttpStatus.OK)) {
                return response.bodyToMono(String.class);
            } else if (response.statusCode()
                    .is4xxClientError()) {
                return Mono.just("Error response");
            } else {
                return response.createException()
                        .flatMap(Mono::error);
            }
        });
    }

    public Mono<String> editRequest(String addEndPoint, Post post) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.PUT);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(JsonPlaceholderWebClient.BASE_URL + addEndPoint);
        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec.bodyValue(post);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        return headersSpec.exchangeToMono(response -> {
            if (response.statusCode()
                    .equals(HttpStatus.OK)) {
                return response.bodyToMono(String.class);
            } else if (response.statusCode()
                    .is4xxClientError()) {
                return Mono.just("Error response");
            } else {
                return response.createException()
                        .flatMap(Mono::error);
            }
        });
    }

    public Mono<String> deleteRequest(String addEndPoint) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.DELETE);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(addEndPoint);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return bodySpec.exchangeToMono(response -> {
            if (response.statusCode()
                    .equals(HttpStatus.OK)) {
                return response.bodyToMono(String.class);
            } else if (response.statusCode()
                    .is4xxClientError()) {
                return Mono.just("Error response");
            } else {
                return response.createException()
                        .flatMap(Mono::error);
            }
        });
    }
}
