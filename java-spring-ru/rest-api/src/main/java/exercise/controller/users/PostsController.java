package exercise.controller.users;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
public class PostsController {

    private List<Post> posts = Data.getPosts();

    @GetMapping("/api/users/{id}/posts")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getPostsByUser(@PathVariable Integer id) {
        List<Post> rs = posts.stream()
                .filter(x -> x.getUserId() == id)
                .toList();

        return rs;
    }

    @PostMapping("/api/users/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createNewPost(@PathVariable Integer id, @RequestBody Post post) {
        Post newPost = new Post();
        newPost.setBody(post.getBody());
        newPost.setTitle(post.getTitle());
        newPost.setSlug(post.getSlug());
        newPost.setUserId(id);

        posts.add(newPost);

        return newPost;
    }

}
// END
