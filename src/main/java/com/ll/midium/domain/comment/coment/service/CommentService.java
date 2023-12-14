package com.ll.midium.domain.comment.coment.service;

import com.ll.midium.domain.comment.coment.entity.Comment;
import com.ll.midium.domain.comment.coment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;


    public void save(Comment comment) {
        commentRepository.save(comment);
    }
}
