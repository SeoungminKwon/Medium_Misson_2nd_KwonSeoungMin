package com.ll.midium.domain.comment.coment.controller;

import com.ll.midium.domain.comment.coment.CommentCreateForm;
import com.ll.midium.domain.comment.coment.entity.Comment;
import com.ll.midium.domain.comment.coment.service.CommentService;
import com.ll.midium.domain.post.post.entity.Post;
import com.ll.midium.domain.post.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;

    @PostMapping("/post/{id}/comment/write")
    public String writeComment(@PathVariable("id") Long id, @Valid CommentCreateForm commentCreateForm, BindingResult bindingResult, Principal principal) {
        Post findPost = postService.findById(id);
        if (bindingResult.hasErrors()) {
            return "domain/post/post/detailPost";
        }
        Comment comment = new Comment();
        comment.setContent(commentCreateForm.getBody());
        comment.setPost(findPost);
        comment.setAuthor(principal.getName());
        commentService.save(comment);
        return "redirect:/post/"+id;
    }

}
