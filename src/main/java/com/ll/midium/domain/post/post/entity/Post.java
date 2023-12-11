package com.ll.midium.domain.post.post.entity;

import com.ll.midium.domain.base.base.entity.BaseEntity;
import com.ll.midium.domain.comment.coment.entity.Coment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long hits;
    private Long recommendation;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List< Coment > coments;

}
