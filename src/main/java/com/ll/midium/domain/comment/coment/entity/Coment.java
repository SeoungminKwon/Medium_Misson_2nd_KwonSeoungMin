package com.ll.midium.domain.comment.coment.entity;

import com.ll.midium.domain.base.base.entity.BaseEntity;
import com.ll.midium.domain.post.post.entity.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.*;

@Getter
@Setter
@Entity
public class Coment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;


}
