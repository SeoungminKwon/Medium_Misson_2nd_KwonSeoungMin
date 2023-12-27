package com.ll.midium.domain.comment.coment.service;

import com.ll.midium.domain.comment.coment.entity.Comment;
import com.ll.midium.domain.comment.coment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;


    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    public Comment findById(Long commentId) {
        Optional< Comment > oc = commentRepository.findById(commentId);
        if(!oc.isPresent()){
            throw new RuntimeException("존재하지 않는 Comment입니다.");
        }
        return oc.get();
    }
}
