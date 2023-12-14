package com.ll.midium.domain.comment.coment;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateForm {

    @NotEmpty(message = "댓글은 비어있을 수 없습니다.")
    private String body;

}
