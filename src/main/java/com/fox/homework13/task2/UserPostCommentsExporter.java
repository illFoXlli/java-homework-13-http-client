package com.fox.homework13.task2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fox.homework13.task2.model.Comment;
import com.fox.homework13.task2.model.Post;
import com.fox.homework13.task2.service.PostService;

import java.io.File;
import java.util.Comparator;
import java.util.List;

public class UserPostCommentsExporter {

    public static void main(String[] args) throws Exception {

        int userId = 1;

        PostService service = new PostService();
        ObjectMapper mapper = new ObjectMapper();

        // читаю посты
        List<Post> posts = service.getPostsByUser(userId);

        // ищу последний
        Post lastPost = posts.stream()
                .max(Comparator.comparingInt(p -> p.id))
                .orElseThrow();

        System.out.println("Last post id: " + lastPost.id);

        // получаю комментарии
        List<Comment> comments = service.getCommentsByPost(lastPost.id);

        // сохраняю в файл
        String fileName = "src/main/resources/user-" + userId + "-post-" + lastPost.id + "-comments.json";

        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File(fileName), comments);

        System.out.println("Saved to file: " + fileName);
    }
}