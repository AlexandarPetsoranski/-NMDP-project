package com.example.demo.client.base;

import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.ClientResponse;

public interface BaseClient {
    ClientResponse sendGet(String url, HttpHeaders headers, MultiValueMap<String, String> queryParams);

    ClientResponse sendPost(String url, HttpHeaders headers, MultiValueMap<String, String> queryParams, Object body, Class<?> bodyType);

    ClientResponse sendDelete(String url, HttpHeaders headers, MultiValueMap<String, String> queryParams);

    ClientResponse sendPatch(String url, HttpHeaders headers, MultiValueMap<String, String> queryParams, Object body, Class<?> bodyType);

    ClientResponse sendPut(String url, HttpHeaders headers, MultiValueMap<String, String> queryParams, Object body, Class<?> bodyType);
}
