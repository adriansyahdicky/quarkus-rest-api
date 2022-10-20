package org.acme.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@RegisterForReflection
public class PostDto {
    private String title;
    private String content;
    private String[] tags;
}
