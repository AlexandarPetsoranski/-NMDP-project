package com.example.demo.client;

import com.example.demo.config.RestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Component
public class JsonPlaceholderWebClient {

    private static final HttpHeaders headers = new HttpHeaders();
    private static final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    WebClient client;

    public static String BASE_URL = "http://jsonplaceholder.typicode.com";

    public static ResponseEntity<String> getRequest(String addEndPoint) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        return restTemplate.exchange(JsonPlaceholderWebClient.BASE_URL + addEndPoint,
                HttpMethod.GET, entity, String.class);
    }

    public static ResponseEntity<String> postRequest(String addEndPoint, PostObject postObject) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<PostObject> entity = new HttpEntity<PostObject>(postObject, headers);

        return restTemplate.exchange(JsonPlaceholderWebClient.BASE_URL + addEndPoint, HttpMethod.POST, entity, String.class);
    }

    public static ResponseEntity<String> editRequest(String addEndPoint, PostObject postObject) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<PostObject> entity = new HttpEntity<PostObject>(postObject, headers);

        return restTemplate.exchange(JsonPlaceholderWebClient.BASE_URL + addEndPoint, HttpMethod.POST, entity, String.class);
    }

    public static ResponseEntity<String> deleteRequest(String addEndPoint, PostObject postObject) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<PostObject> entity = new HttpEntity<PostObject>(postObject, headers);

        return restTemplate.exchange(JsonPlaceholderWebClient.BASE_URL + addEndPoint, HttpMethod.POST, entity, String.class);
    }
}
