package com.ll.midium.domain.post.post.controller;

import com.ll.midium.domain.comment.coment.CommentCreateForm;
import com.ll.midium.domain.post.post.PostCreateForm;
import com.ll.midium.domain.post.post.entity.Post;
import com.ll.midium.domain.post.post.service.PostService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;

    @GetMapping("/post/list")
    public String showList(Model model) {
        model.addAttribute("allPost", postService.getAllPost());
        return "domain/post/post/listpage";
    }

    @GetMapping("/post/write")
    @PreAuthorize("isAuthenticated()")
    public String postWrite(PostCreateForm postCreateForm) {
        return "domain/post/post/write_form";
    }

    @PostMapping("/post/write")
    @PreAuthorize("isAuthenticated()")
    public String postWrite(@Valid PostCreateForm postCreateForm, BindingResult bindingResult, Principal principal) {

        String name = principal.getName();
        if (bindingResult.hasErrors()) {
            return "domain/post/post/write_form";
        }
        postService.createPost(postCreateForm, name);
        return "redirect:/post/list";
    }

    @GetMapping("/post/{id}")
    public String detailPost(@PathVariable("id") Long id, Model model) {
        Post findPost = postService.findById(id);
        model.addAttribute("post", findPost);
        CommentCreateForm commentCreateForm = new CommentCreateForm();
        model.addAttribute("commentCreateForm", commentCreateForm);

        return "domain/post/post/detailPost";
    }

    @GetMapping("/post/{id}/modify")
    @PreAuthorize("isAuthenticated()")
    public String modifyPost(@PathVariable("id") Long id, Model model, Principal principal){
        Post findPost = postService.findById(id);
        if(!findPost.getAuthor().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이없습니다.");
        }
        model.addAttribute("post", findPost);
        return "domain/post/post/modify_form";
    }

    @PostMapping("/post/{id}/modify")
    public String modifyPost(@Valid PostCreateForm postCreateForm, BindingResult bindingResult, @PathVariable("id") Long id){
        if(bindingResult.hasErrors()){
            return "/post/"+id+"/modify";
        }
        postService.modifyPost(id,postCreateForm);
        return "redirect:/post/list";
    }

    @GetMapping("/post/{id}/delete")
    @PreAuthorize("isAuthenticated()")
    public String deletePost(@PathVariable("id") Long id, Principal principal){
        Post findPost = postService.findById(id);
        if(!findPost.getAuthor().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이없습니다.");
        }
        postService.delete(id);
        return "redirect:/post/list";
    }

    @GetMapping("/b/{username}")
    public String userPost(Model model, @PathVariable("username") String username) {
        List< Post > findAuthorPosts = postService.findByAuthor(username);
        model.addAttribute("allPost", findAuthorPosts);
        return "domain/post/post/user_post_page";
    }

    @GetMapping("/post/myList")
    public String myPost(Principal principal, Model model) {
        String name = principal.getName();
        List< Post > myPostList = postService.findByAuthor(principal.getName());
        model.addAttribute("allPost", myPostList);
        return "domain/post/post/user_post_page";
    }

    @PostMapping("/post/{id}/increaseHit")
    public String increaseHit(@PathVariable("id") Long id , HttpSession session) {
        Set<Long>viewedPosts = (Set<Long>)session.getAttribute("viewedPosts");
        if (viewedPosts == null) {
            viewedPosts = new HashSet<>();
            viewedPosts.add(id);
            session.setAttribute("viewedPosts", viewedPosts);
            postService.increaseHit(id);
        }else{
            if(!viewedPosts.contains(id)){
                postService.increaseHit(id);
            }
        }
        return "redirect:/";
    }
}
