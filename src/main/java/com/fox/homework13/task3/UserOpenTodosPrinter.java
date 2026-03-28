package com.fox.homework13.task3;

import com.fox.homework13.task3.model.Todo;
import com.fox.homework13.task3.service.TodoService;

import java.util.List;

public class UserOpenTodosPrinter {

    public static void main(String[] args) throws Exception {

        int userId = 1;

        TodoService service = new TodoService();

        List<Todo> todos = service.getTodosByUser(userId);

        System.out.println("=== OPEN TODOS ===");

        todos.stream()
                .filter(todo -> !todo.completed)
                .forEach(todo ->
                        System.out.println(todo.id + " - " + todo.title)
                );
    }
}