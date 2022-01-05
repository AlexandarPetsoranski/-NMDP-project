package com.example.demo.client.baseClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class BaseClientImpl implements BaseClient{

    @Autowired
    WebClient webClient;

    public ClientResponse sendGet(String url, HttpHeaders headers, MultiValueMap<String, String> queryParams) {
        return this.webClient.get().uri(url, (uriBuilder) -> uriBuilder.queryParams(queryParams).build()).headers((httpHeaders) -> {
            httpHeaders.addAll(headers);
        }).exchange().block();
    }

    public ClientResponse sendPost(String url, HttpHeaders headers, MultiValueMap<String, String> queryParams, Object body, Class<?> bodyType) {
        return this.webClient.post().uri(url, (uriBuilder) -> uriBuilder.queryParams(queryParams).build()).body(BodyInserters.fromProducer(Mono.just(body), bodyType)).headers((httpHeaders) -> {
            httpHeaders.addAll(headers);
        }).exchange().block();
    }

    public ClientResponse sendDelete(String url, HttpHeaders headers, MultiValueMap<String, String> queryParams) {
        return this.webClient.delete().uri(url, (uriBuilder) -> uriBuilder.queryParams(queryParams).build()).headers((httpHeaders) -> {
            httpHeaders.addAll(headers);
        }).exchange().block();
    }

    public ClientResponse sendPatch(String url, HttpHeaders headers, MultiValueMap<String, String> queryParams, Object body, Class<?> bodyType) {
        return this.webClient.patch().uri(url, (uriBuilder) -> uriBuilder.queryParams(queryParams).build()).body(BodyInserters.fromProducer(Mono.just(body), bodyType)).headers((httpHeaders) -> {
            httpHeaders.addAll(headers);
        }).exchange().block();
    }

    public ClientResponse sendPut(String url, HttpHeaders headers, MultiValueMap<String, String> queryParams, Object body, Class<?> bodyType) {
        return this.webClient.put().uri(url, (uriBuilder) -> uriBuilder.queryParams(queryParams).build()).body(BodyInserters.fromProducer(Mono.just(body), bodyType)).headers((httpHeaders) -> {
            httpHeaders.addAll(headers);
        }).exchange().block();
    }
}
