package com.ll.midium.domain.post.post;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreateForm {
    @NotEmpty(message = "제목은 비어있을수 없습니다.")
    private String title;

    @NotEmpty(message = "내용은 비어있을수 없습니다.")
    private String content;

    private Boolean isPublish;
}
