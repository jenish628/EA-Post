package edu.miu.post.controller;

import edu.miu.post.dto.PostDto;
import edu.miu.post.entity.Post;
import edu.miu.post.requests.PostRequest;
import edu.miu.post.service.IPostService;
import edu.miu.post.service.PostService;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.UploadObjectArgs;
import io.minio.errors.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/posts")
@RestController
public class PostController {

    @Resource
    MinioClient minioClient;
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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addPost(@RequestBody @ModelAttribute PostRequest post) throws Exception {
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
