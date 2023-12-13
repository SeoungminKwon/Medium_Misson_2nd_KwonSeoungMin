package com.ll.midium.domain.post.post.repository;

import com.ll.midium.domain.post.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository< Post, Long> {
    @Query(value = "select p from Post p where p.isPublish = true order by p.id desc")
    List< Post> findTop30ByOrderByIdDesc();

    @Query(value = "select p from Post p where p.isPublish = true order by p.id desc")
    List< Post> findAllByOrderByIdDesc();

    List< Post> findByAuthorOrderByIdDesc(String username);
}
