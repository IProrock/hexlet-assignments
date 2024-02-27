package exercise.controller;

import exercise.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @GetMapping()
    public List<Comment> getAllComments() {
        List<Comment> rs = commentRepository.findAll();

        return rs;
    }

    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable Long id) {
        Comment rs = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " Not found"));

        return rs;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createNewComment(@RequestBody Comment comment) {
        Comment newComment = new Comment();

        newComment.setBody(comment.getBody());
        newComment.setPostId(comment.getPostId());

        commentRepository.save(newComment);

        return newComment;
    }

    @PutMapping("/{id}")
    public Comment updateCommentById(@PathVariable Long id, @RequestBody Comment comment) {
        Comment rs = commentRepository.findById(id).get();

        rs.setBody(comment.getBody());
        rs.setPostId(comment.getPostId());

        commentRepository.save(rs);

        return rs;
    }

    @DeleteMapping("/{id}")
    public void deleteCommentById(@PathVariable Long id) {
        commentRepository.deleteById(id);
    }
}
// END
