package edu.miu.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Verification {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
}
