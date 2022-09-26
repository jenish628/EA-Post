package edu.miu.post.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
public class PostDto {

    private Long id;
    private String title;
    private String description;
    private List<String> files;
    List<PostCommentDto> comments;


    public void setComment(PostCommentDto commentDto) {
        if(comments == null){
            comments = new ArrayList<>();
        }
        comments.add(commentDto);
    }

}
