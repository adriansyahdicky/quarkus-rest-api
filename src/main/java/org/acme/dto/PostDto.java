package org.acme.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@RegisterForReflection
public class PostDto {
    @NotBlank(message = "Title Not Required")
    private String title;
    @NotBlank(message = "Content Not Required")
    private String content;
    @NotNull(message = "Tags Not Required")
    private String[] tags;
}
