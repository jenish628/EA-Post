package edu.miu.post.controller;

import edu.miu.post.dto.PostDto;
import edu.miu.post.entity.Post;
import edu.miu.post.service.IPostService;
import edu.miu.post.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/api/posts")
@RestController
public class PostController {

    @Resource
    private IPostService postService;

    @GetMapping
    public List<PostDto> getAll(){
        return postService.findAll();
    }

    @GetMapping("/{id}")
    public PostDto getPostById(@PathVariable long id){
        return postService.findById(id);
    }

    @PostMapping
    public void addPost(@RequestBody PostDto post){
        postService.save(post);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable long id){
        postService.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updatePost(
            @RequestBody PostDto post,
            @PathVariable long id){

        postService.update(post,id);
    }




}
