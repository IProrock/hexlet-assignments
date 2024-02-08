package exercise;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getPosts(@RequestParam(defaultValue = "10") Integer limit) {
        List<Post> rs = posts.stream().limit(limit).toList();

        return ResponseEntity.ok().header("X-Total-Count", String.valueOf(posts.size())).body(rs);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Optional<Post>> getPost(@PathVariable String id) {
        Optional<Post> post = posts.stream().filter(x -> x.getId().equals(id)).findFirst();
        HttpStatus st = HttpStatus.valueOf(200);
        if(!post.isPresent()) {
            st = HttpStatus.valueOf(404);
        }

        return ResponseEntity.status(st).body(post);
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> newPost(@RequestBody Post body) {
        posts.add(body);

        return ResponseEntity.status(201).body(body);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post body) {
        Optional<Post> ppost = posts.stream().filter(x -> x.getId().equals(id)).findFirst();
        HttpStatus st = HttpStatus.valueOf(204);

        if (ppost.isPresent()) {
            Post post = ppost.get();
            post.setTitle(body.getTitle());
            post.setBody(body.getBody());
            st = HttpStatus.valueOf(200);
        }

        return ResponseEntity.status(st).body(body);
    }
    // END

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
