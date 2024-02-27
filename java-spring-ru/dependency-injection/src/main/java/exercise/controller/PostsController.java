package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;

    @GetMapping()
    public List<Post> getAllPosts() {
        List<Post> rs = postRepository.findAll();

        return rs;
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        Post rs = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));

        return rs;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Post createNewPost(@RequestBody Post post) {
        Post newPost = new Post();

        newPost.setTitle(post.getTitle());
        newPost.setBody(post.getBody());

        postRepository.save(newPost);

        return newPost;
    }

    @PutMapping("/{id}")
    public Post updatePostById(@PathVariable Long id, @RequestBody Post post) {
        Post postToUpdate = postRepository.findById(id).get();

        postToUpdate.setBody(post.getBody());
        postToUpdate.setTitle(post.getTitle());

        postRepository.save(postToUpdate);

        return postToUpdate;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        postRepository.deleteById(id);
        long comments = commentRepository.deleteByPostId(id);
    }



}
// END
