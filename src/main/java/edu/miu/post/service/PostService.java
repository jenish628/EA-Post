package edu.miu.post.service;

import edu.miu.post.dto.PostDto;
import edu.miu.post.entity.Post;
import edu.miu.post.repository.PostRepository;
import eye2web.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class PostService implements IPostService {

    @Resource
    private PostRepository postRepository;

    @Resource
    private ModelMapper modelMapper;

    @Override
    public List<PostDto> findAll() {

        return postRepository.findAll().stream()
                .map(a -> modelMapper.map(a, PostDto.class)).collect(Collectors.toList());

    }

    @Override
    public PostDto findById(long id) {
        var post =  postRepository.findById(id).get();
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public void update(PostDto post, long id) {
         postRepository.save(modelMapper.map(post, Post.class));
    }

    @Override
    public void deleteById(long id) {
        postRepository.deleteById(id);
    }

    @Override
    public void save(PostDto post) {
        postRepository.save(modelMapper.map(post,Post.class));
    }
}
