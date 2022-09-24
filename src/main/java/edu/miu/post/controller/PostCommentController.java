package edu.miu.post.controller;

import edu.miu.post.dto.PostCommentDto;
import edu.miu.post.service.PostCommentProxyClient;
import edu.miu.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostCommentController {


    @Autowired
    private PostCommentProxyClient postCommentProxyClient;
    @Autowired
    private PostService postService;

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity getAllCommentByPost(@PathVariable Long postId) {
        return ResponseEntity.ok().body(postCommentProxyClient.getAllCommentByPost(postId));

    }


    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity savePostComment(@PathVariable Long postId, @RequestBody PostCommentDto postCommentDto) {
        if (postService.findById(postId) == null) {
            return ResponseEntity.internalServerError().body("Post not found");
        } else {
            postCommentDto.setPostId(postId);
            postCommentProxyClient.saveCommentByPost(postCommentDto);
            return ResponseEntity.ok().build();
        }

    }


    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity deleteComment(@PathVariable Long commentId) {
        postCommentProxyClient.deleteCommentId(commentId);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity updateComment(@PathVariable Long commentId, @PathVariable Long postId, @RequestBody PostCommentDto commentDto) {
        commentDto.setPostId(postId);
        postCommentProxyClient.updateComment(commentId, commentDto);
        return ResponseEntity.ok().build();
    }


}
