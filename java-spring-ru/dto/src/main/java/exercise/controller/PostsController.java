package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
public class PostsController {

    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/posts")
    public List<PostDTO> getAllPosts() {
        List<PostDTO> rs = new ArrayList<>();

        List<Post> postsList = postRepository.findAll();

        for (Post post : postsList) {
            PostDTO postDTO = new PostDTO();
            postDTO.setBody(post.getBody());
            postDTO.setId(post.getId());
            postDTO.setTitle(post.getTitle());

            List<Comment> comments = commentRepository.findByPostId(post.getId());
            List<CommentDTO> commentsDto = new ArrayList<>();

            for (Comment comment : comments) {
                CommentDTO commentDTO = new CommentDTO();
                commentDTO.setBody(comment.getBody());
                commentDTO.setId(comment.getId());
                commentsDto.add(commentDTO);

            }

            postDTO.setComments(commentsDto);

            rs.add(postDTO);
        }

        return rs;

    }

    @GetMapping("/posts/{id}")
    public PostDTO getPostById(@PathVariable Long id) {

        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post with id " +id + " not found"));

        PostDTO postDTO = new PostDTO();

        postDTO.setId(post.getId());
        postDTO.setBody(post.getBody());
        postDTO.setTitle(post.getTitle());

        List<CommentDTO> commentDTOS = new ArrayList<>();

        List<Comment> comments = commentRepository.findByPostId(id);

        for (Comment comment : comments) {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setBody(comment.getBody());
            commentDTO.setId(comment.getId());
            commentDTOS.add(commentDTO);
        }

        postDTO.setComments(commentDTOS);

        return postDTO;
    }
}
// END
