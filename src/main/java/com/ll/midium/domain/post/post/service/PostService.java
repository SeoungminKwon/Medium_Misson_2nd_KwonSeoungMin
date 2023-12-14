package com.ll.midium.domain.post.post.service;

import com.ll.midium.domain.post.post.PostCreateForm;
import com.ll.midium.domain.post.post.entity.Post;
import com.ll.midium.domain.post.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public List< Post > get30Posters() {
        List< Post > top30ByOrderByIdDesc = postRepository.findTop30ByOrderByIdDesc();
        try{
            return postRepository.findTop30ByOrderByIdDesc();
        }catch (NullPointerException e){
            return postRepository.findAll();
        }
    }

    public List< Post> getAllPost() {
        return postRepository.findAllByOrderByIdDesc();

    }


    public void createPost(PostCreateForm postCreateForm, String name) {
        Post post = new Post();
        post.setTitle(postCreateForm.getTitle());
        post.setContent(postCreateForm.getContent());
        post.setPublish(postCreateForm.getIsPublish());
        post.setAuthor(name);
        postRepository.save(post);
    }

    public Post findById(Long id) {
        Optional< Post > op = postRepository.findById(id);
        return op.get();
    }

    public void modifyPost(Long id, PostCreateForm postCreateForm) {
        Optional< Post > op = postRepository.findById(id);
        if (!op.isPresent()) {
            throw new RuntimeException("존재하지않는 post입니다.");
        }
        Post post = op.get();
        post.setPublish(postCreateForm.getIsPublish());
        post.setTitle(postCreateForm.getTitle());
        post.setContent(postCreateForm.getContent());
        postRepository.save(post);
    }

    public void delete(Long id) {
        Optional< Post > op = postRepository.findById(id);
        if (!op.isPresent()) {
            throw new RuntimeException("존재하지않는 post입니다.");
        }
        Post post = op.get();
        postRepository.delete(post);
    }

    public List<Post> findByAuthor(String username) {
        return postRepository.findByAuthorOrderByIdDesc(username);
    }

    public void increaseHit(Long id) {

        Set<Long> viewedPosts = new HashSet<>();

        Optional< Post > op = postRepository.findById(id);
        if (!op.isPresent()) {
            throw new RuntimeException("존재하지않는 post입니다.");
        }
        Post post = op.get();
        if (post.getHits() == null) {
            post.setHits(1L);
        }else{
            post.setHits(post.getHits()+1);
        }
        postRepository.save(post);
    }

    public void savePost(Post post){
        postRepository.save(post);

    }
}

