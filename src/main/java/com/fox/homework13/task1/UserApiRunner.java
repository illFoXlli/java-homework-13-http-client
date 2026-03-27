package com.fox.homework13.task1;

import com.fox.homework13.task1.model.User;
import com.fox.homework13.task1.service.UserService;

import java.util.List;

public class UserApiRunner {

    public static void main(String[] args) throws Exception {

        UserService service = new UserService();

        // CREATE
        User user = new User();
        user.name = "Test";
        user.username = "test123";
        user.email = "test@test.com";

        User created = service.createUser(user);

        System.out.println("=== CREATE ===");
        System.out.println("Created user id: " + created.id);

        // GET ALL
        List<User> allUsers = service.getAllUsers();

        if (allUsers.isEmpty()) {
            throw new RuntimeException("No users found");
        }

        // беру существующего пользователя а то апи гавно
        User existingUser = allUsers.get(0);

        // UPDATE
        existingUser.name = "Updated Name";
        User updated = service.updateUser(existingUser.id, existingUser);

        System.out.println("\n=== UPDATE ===");
        System.out.println("Updated user: " + updated);

        // DELETE
        int status = service.deleteUser(existingUser.id);

        System.out.println("\n=== DELETE ===");
        System.out.println("Delete status: " + status);

        // GET ALL
        System.out.println("\n=== GET ALL ===");
        System.out.println("Total users: " + allUsers.size());
        allUsers.forEach(System.out::println);

        // GET BY ID
        User userById = service.getUserById(1);

        System.out.println("\n=== GET BY ID ===");
        System.out.println(userById);

        // GET BY USERNAME
        List<User> usersByUsername = service.getUserByUsername("Bret");

        System.out.println("\n=== GET BY USERNAME ===");
        usersByUsername.forEach(System.out::println);
    }
}