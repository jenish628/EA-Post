package edu.miu.post.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCommentDto {
    private Long id;
    private String comment;
    private Long postId;
    private LocalDate createDate;
}
