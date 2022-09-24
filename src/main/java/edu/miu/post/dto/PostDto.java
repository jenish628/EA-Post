package edu.miu.post.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class PostDto {

    private Long id;
    private String title;
    private String description;
    private List<String> files;

}
