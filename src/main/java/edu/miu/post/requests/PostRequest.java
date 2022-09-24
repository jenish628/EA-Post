package edu.miu.post.requests;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
public class PostRequest {
    private String title;
    private String description;
    private List<MultipartFile> files;
}
