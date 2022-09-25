package edu.miu.post.service;

import edu.miu.post.dto.PostDto;
import edu.miu.post.entity.Post;
import edu.miu.post.requests.PostRequest;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface IPostService {

    List<PostDto> findAll();
    PostDto findById(long id);
    void update(PostDto post, long id);
    void deleteById(long id);
    void save(PostRequest request) throws Exception;

    List<Long> findAllPostId();
}
