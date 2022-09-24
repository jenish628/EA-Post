package edu.miu.post.service;


import edu.miu.post.dto.PostCommentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "post-commnet-service", url = "http://localhost:8082/api/")
public interface PostCommentProxyClient {

    @RequestMapping(method = RequestMethod.GET, value = "/comments", produces = "application/json")
    List<PostCommentDto> getAllCommentByPost(@RequestParam Long postId);


    @RequestMapping(method = RequestMethod.POST, value = "/comments", produces = "application/json")

    void saveCommentByPost(@RequestBody PostCommentDto postCommentDto);


    @RequestMapping(method = RequestMethod.DELETE, value = "/comments/{commentId}", produces = "application/json")
    void deleteCommentId(@PathVariable Long commentId);

    @RequestMapping(method = RequestMethod.PUT, value = "/comments/{commentId}", produces = "application/json")
    void updateComment(@PathVariable Long commentId, @RequestBody PostCommentDto commentDto);
}
