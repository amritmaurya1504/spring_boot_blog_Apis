package com.rajamrit.SpringBoot_Blog_app.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CommentDTO {

    private Integer commentId;

    @NotNull
    @NotBlank
    @Size(min = 4)
    private String comment;

}
