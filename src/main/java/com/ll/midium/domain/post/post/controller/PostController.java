package com.ll.midium.domain.post.post.controller;

import com.ll.midium.domain.post.post.PostCreateForm;
import com.ll.midium.domain.post.post.entity.Post;
import com.ll.midium.domain.post.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String postWrite(PostCreateForm postCreateForm) {
        return "domain/post/post/write_form";
    }

    @PostMapping("/post/write")
    public String postWrite(@Valid PostCreateForm postCreateForm, BindingResult bindingResult) {

        System.out.println(postCreateForm);

        if (bindingResult.hasErrors()) {
            return "/post/write";
        }
        postService.createPost(postCreateForm);
        return "redirect:/";
    }

    @GetMapping("/post/{id}")
    public String detailPost(@PathVariable("id") Long id, Model model) {
        Post findPost = postService.findById(id);
        model.addAttribute("post", findPost);

        return "domain/post/post/detailPost";
    }

    @GetMapping("/post/{id}/modify")
    public String modifyPost(@PathVariable("id") Long id, Model model){
        Post findPost = postService.findById(id);
        model.addAttribute("post", findPost);
        return "domain/post/post/modify_form";
    }

    @PostMapping("/post/{id}/modify")
    public String modifyPost(@Valid PostCreateForm postCreateForm, BindingResult bindingResult, @PathVariable("id") Long id){
        if(bindingResult.hasErrors()){
            return "/post/"+id+"/modify";
        }
        postService.modifyPost(id,postCreateForm);
        return "redirect:/";
    }

    @GetMapping("/post/{id}/delete")
    public String deletePost(@PathVariable("id") Long id){
        postService.delete(id);
        return "redirect:/";
    }
}
