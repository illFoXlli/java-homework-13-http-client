package com.fox.homework13.task2.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fox.homework13.task2.model.Comment;
import com.fox.homework13.task2.model.Post;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.util.Arrays;
import java.util.List;

public class PostService {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    // получить посты пользователя
    public List<Post> getPostsByUser(int userId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/users/" + userId + "/posts"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Post[] posts = mapper.readValue(response.body(), Post[].class);

        return Arrays.asList(posts);
    }

    // получить комментарии к посту
    public List<Comment> getCommentsByPost(int postId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/posts/" + postId + "/comments"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Comment[] comments = mapper.readValue(response.body(), Comment[].class);

        return Arrays.asList(comments);
    }
}