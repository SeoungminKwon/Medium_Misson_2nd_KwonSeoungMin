package com.ll.midium.domain.post.post.entity;

import com.ll.midium.domain.comment.coment.entity.Comment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class Post{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String title;
    private String content;
    private Long hits;
    private Long recommendation;
    private boolean isPublish;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List< Comment > comments;

}
