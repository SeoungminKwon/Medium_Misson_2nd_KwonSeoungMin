package com.ll.midium.domain.comment.coment.repository;

import com.ll.midium.domain.comment.coment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository< Comment, Long> {
}
