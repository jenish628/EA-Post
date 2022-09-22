package edu.miu.post.service;

import edu.miu.post.dto.PostDto;
import edu.miu.post.entity.Post;

import java.util.List;

public interface IPostService {

    List<PostDto> findAll();
    PostDto findById(long id);
    void update(PostDto post, long id);
    void deleteById(long id);
    void save(PostDto post);

}
