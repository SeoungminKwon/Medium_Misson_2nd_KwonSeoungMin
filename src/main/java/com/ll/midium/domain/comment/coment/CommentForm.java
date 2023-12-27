package com.ll.midium.domain.comment.coment;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentForm {
    @NotEmpty(message = "내용은 비어있을수없습니다")
    private String content;
}
