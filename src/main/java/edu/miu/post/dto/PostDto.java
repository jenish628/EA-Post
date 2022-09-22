package edu.miu.post.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class PostDto {

    private Long id;
    private String title;
    private String description;

}
