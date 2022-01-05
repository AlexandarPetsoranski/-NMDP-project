package com.example.demo.client;

import com.example.demo.client.baseClient.BaseClientImpl;
import com.example.demo.projectVeriables.ProjectVeriables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.ClientResponse;

import java.util.List;

@Component
public class JsonPlaceholderPostWebClient {

    private final HttpHeaders httpHeaders = new HttpHeaders();

    @Autowired
    BaseClientImpl baseClientImpl;

    public Post getPost(int id) {
        MultiValueMap queryParams = new LinkedMultiValueMap();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        ClientResponse clientResponse = baseClientImpl.sendGet(String.format(ProjectVeriables.BASE_URL + "/posts/%d", id), httpHeaders, queryParams);
        return clientResponse.bodyToMono(Post.class).block();
    }

    public Post createPost(Post post) {
        MultiValueMap queryParams = new LinkedMultiValueMap();
        ClientResponse clientResponse = baseClientImpl.sendPost(ProjectVeriables.BASE_URL + "/posts", httpHeaders, queryParams, post, Post.class);
        return clientResponse.bodyToMono(Post.class).block();
    }

    public Post deletePost(int id) {
        MultiValueMap queryParams = new LinkedMultiValueMap();
        ClientResponse clientResponse = baseClientImpl.sendDelete(String.format(ProjectVeriables.BASE_URL + "/posts/%d", id), httpHeaders, queryParams);
        return clientResponse.bodyToMono(Post.class).block();
    }

    public Post patchPost(int id, Post post) {
        MultiValueMap queryParams = new LinkedMultiValueMap();
        ClientResponse clientResponse = baseClientImpl.sendPatch(String.format(ProjectVeriables.BASE_URL + "/posts/%d", id), httpHeaders, queryParams, post, Post.class);
        return clientResponse.bodyToMono(Post.class).block();
    }

    public Post putPost(int id, Post post) {
        MultiValueMap queryParams = new LinkedMultiValueMap();
        ClientResponse clientResponse = baseClientImpl.sendPut(String.format(ProjectVeriables.BASE_URL + "/posts/%d", id), httpHeaders, queryParams, post, Post.class);
        return clientResponse.bodyToMono(Post.class).block();
    }

    public List<Post> getAllPosts() {
        MultiValueMap queryParams = new LinkedMultiValueMap();
        ClientResponse clientResponse = baseClientImpl.sendGet(ProjectVeriables.BASE_URL + "/posts", httpHeaders, queryParams);
        return clientResponse.bodyToMono(new ParameterizedTypeReference<List<Post>>() {
        }).block();
    }
}
