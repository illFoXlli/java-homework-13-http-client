package com.fox.homework13.task3.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fox.homework13.task3.model.Todo;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.util.Arrays;
import java.util.List;

public class TodoService {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public List<Todo> getTodosByUser(int userId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/users/" + userId + "/todos"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Todo[] todos = mapper.readValue(response.body(), Todo[].class);

        return Arrays.asList(todos);
    }
}