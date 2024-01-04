package com.ll.midium.domain.post.post.controller;

import com.ll.midium.domain.post.post.entity.Post;
import com.ll.midium.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserPostController {
    private final PostService postService;
    @GetMapping("/b/{username}")
    public String userPost(Model model, @PathVariable("username") String username) {
        List< Post > findAuthorPosts = postService.findByAuthor(username);
        model.addAttribute("allPost", findAuthorPosts);
        return "domain/post/post/user_post_page";
    }
}
