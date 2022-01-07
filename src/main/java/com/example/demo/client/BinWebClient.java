package com.example.demo.client;

import com.example.demo.client.base.BaseClientImpl;
import com.example.demo.projectVeriables.ProjectVeriables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class BinWebClient {
    private final HttpHeaders httpHeaders = new HttpHeaders();

    @Autowired
    BaseClientImpl baseClientImpl;

    public ClientResponse httpBasicAuth(String userName, String password) {
        MultiValueMap queryParams = new LinkedMultiValueMap();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBasicAuth(userName,password);
        ClientResponse clientResponse = baseClientImpl.sendGet(String.format(ProjectVeriables.BIN_BASE_URL + "/basic-auth/%s/%s", userName, password), httpHeaders, queryParams);
        return clientResponse.bodyToMono(ClientResponse.class).block();
    }

    public ClientResponse bearerAuthentication(String token) {
        MultiValueMap queryParams = new LinkedMultiValueMap();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.TEXT_HTML);
        httpHeaders.setBearerAuth(token);
        ClientResponse clientResponse = baseClientImpl.sendGet((ProjectVeriables.BIN_BASE_URL + "/bearer" ), httpHeaders, queryParams);
        return clientResponse.bodyToMono(ClientResponse.class).block();
    }

    public ClientResponse digestAuthentication(String qop, String userName, String password) {
        MultiValueMap queryParams = new LinkedMultiValueMap();
        httpHeaders.setAccept(List.of(MediaType.TEXT_HTML));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBasicAuth(userName,password);
        ClientResponse clientResponse = baseClientImpl.sendGet(String.format(ProjectVeriables.BIN_BASE_URL + "/digest-auth/%s/%s/%s", qop,userName, password), httpHeaders, queryParams);
        return clientResponse.bodyToMono(ClientResponse.class).block();
    }

    public ClientResponse digestAuthenticationAndAlgorithm(String qop, String userName, String password) {
        MultiValueMap queryParams = new LinkedMultiValueMap();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        ClientResponse clientResponse = baseClientImpl.sendGet(String.format(ProjectVeriables.BIN_BASE_URL + "/basic-auth/%1$s/%2$s/%3$s", qop,userName, password), httpHeaders, queryParams);
        return clientResponse.bodyToMono(ClientResponse.class).block();
    }
}
