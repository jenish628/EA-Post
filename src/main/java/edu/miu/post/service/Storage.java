package edu.miu.post.service;

import org.springframework.web.multipart.MultipartFile;

public interface Storage {

    String uploadFile(MultipartFile file) throws Exception;

}
