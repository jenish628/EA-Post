package edu.miu.post.service;

import edu.miu.post.dto.PostDto;
import edu.miu.post.entity.Document;
import edu.miu.post.entity.Post;
import edu.miu.post.repository.PostRepository;
import edu.miu.post.requests.PostRequest;
import eye2web.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class PostService implements IPostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Storage storage;

    @Override
    public List<PostDto> findAll() {

        return postRepository.findAll().stream()
                .map(a -> modelMapper.map(a, PostDto.class)).collect(Collectors.toList());

    }

    @Override
    public PostDto findById(long id) {
        var post = postRepository.findById(id).orElseThrow();
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
    public List<Long> findAllPostId() {
        return postRepository.findAllProjectId();
    }

    @Override
    public void save(PostRequest request) throws Exception {
        Post post = modelMapper.map(request, Post.class);

        if (request.getFiles() != null) {
            for (MultipartFile file : request.getFiles()) {
                String fileName = storage.uploadFile(file);
                var document = new Document();
                document.setUrl(fileName);
                post.addDocument(document);
            }
        }

        postRepository.save(post);
    }
}
