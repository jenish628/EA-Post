package edu.miu.post.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VerifyRequest {
    private String email;
}
