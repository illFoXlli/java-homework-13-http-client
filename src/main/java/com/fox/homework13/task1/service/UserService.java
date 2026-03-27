package com.fox.homework13.task1.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fox.homework13.task1.model.User;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.util.Arrays;
import java.util.List;

public class UserService {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/users";

    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    // CREATE
    public User createUser(User user) throws IOException, InterruptedException {
        String body = mapper.writeValueAsString(user);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return mapper.readValue(response.body(), User.class);
    }

    // UPDATE
    public User updateUser(int id, User user) throws IOException, InterruptedException {
        String body = mapper.writeValueAsString(user);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("UPDATE response: " + response.body());

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            try {
                return mapper.readValue(response.body(), User.class);
            } catch (Exception e) {
                throw new RuntimeException("Invalid JSON response: " + response.body());
            }
        } else {
            throw new RuntimeException("Request failed: " + response.statusCode());
        }
    }

    // DELETE
    public int deleteUser(int id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.statusCode();
    }

    // GET ALL
    public List<User> getAllUsers() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        User[] users = mapper.readValue(response.body(), User[].class);

        return Arrays.asList(users);
    }

    // GET BY ID
    public User getUserById(int id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return mapper.readValue(response.body(), User.class);
    }

    // GET BY USERNAME
    public List<User> getUserByUsername(String username) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "?username=" + username))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        User[] users = mapper.readValue(response.body(), User[].class);

        return Arrays.asList(users);
    }
}