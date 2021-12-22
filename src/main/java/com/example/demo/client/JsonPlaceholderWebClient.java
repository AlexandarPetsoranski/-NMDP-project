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

    public Object sendRequestWithID(MethodTypeEnum methodType, EndPointEnum endPoint, int elementId, Post requestBody) {
        WebClient.RequestBodySpec bodySpec;
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec;
        WebClient.RequestHeadersSpec<?> headersSpec;
        Post post;
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        switch (methodType) {
            case GET:
                uriSpec = client.method(HttpMethod.GET);
                bodySpec = uriSpec.uri(String.format(ProjectVeriables.BASE_URL + endPoint, elementId));
                logger.info("Sending GET request to : " + ProjectVeriables.BASE_URL + endPoint + "and getting element with ID" + elementId);
                post = Objects.requireNonNull(bodySpec.exchange().block(), "Response should be not null")
                        .bodyToMono(Post.class).block();
                break;
            case PUT:
                uriSpec = client.method(HttpMethod.PUT);
                bodySpec = uriSpec.uri(String.format(ProjectVeriables.BASE_URL + endPoint, elementId));
                headersSpec = bodySpec.bodyValue(requestBody);
                logger.info("Sending PUT request to : " + ProjectVeriables.BASE_URL + endPoint + "and changing element with ID" + elementId);
                post = Objects.requireNonNull(headersSpec.exchange().block(), "Response should be not null")
                        .bodyToMono(Post.class).block();
                break;
            case DELETE:
                uriSpec = client.method(HttpMethod.DELETE);
                bodySpec = uriSpec.uri(String.format(ProjectVeriables.BASE_URL + endPoint, elementId));
                logger.info("Sending DELETE request to : " + ProjectVeriables.BASE_URL + endPoint + "and deleting element with ID" + elementId);
                post = Objects.requireNonNull(bodySpec.exchange().block(), "Response should be not null")
                        .bodyToMono(Post.class).block();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + methodType);
        }
        return post;
    }

    public List<Object> sendRequestToAll(MethodTypeEnum methodType, EndPointEnum endPoint, Post requestBody) {
        WebClient.RequestBodySpec bodySpec;
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec;
        WebClient.RequestHeadersSpec<?> headersSpec;
        List<Object> allPosts;
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        switch (methodType) {
            case GET:
                uriSpec = client.method(HttpMethod.GET);
                bodySpec = uriSpec.uri(ProjectVeriables.BASE_URL + endPoint);
                logger.info("Sending GET request to : " + ProjectVeriables.BASE_URL + endPoint + "and getting all elements");
                allPosts = Objects.requireNonNull(bodySpec.exchange().block(), "Response should be not null")
                        .bodyToMono(new ParameterizedTypeReference<List<Object>>() {
                        }).block();
                break;

            case POST:
                uriSpec = client.method(HttpMethod.POST);
                bodySpec = uriSpec.uri(ProjectVeriables.BASE_URL + endPoint);
                logger.info("Sending POST request to : " + ProjectVeriables.BASE_URL + endPoint + "and creating new element");
                headersSpec = bodySpec.bodyValue(requestBody);
                allPosts = Objects.requireNonNull(headersSpec.exchange().block(), "Response should be not null")
                        .bodyToMono(new ParameterizedTypeReference<List<Object>>() {
                        }).block();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + methodType);
        }
        return allPosts;
    }
}
