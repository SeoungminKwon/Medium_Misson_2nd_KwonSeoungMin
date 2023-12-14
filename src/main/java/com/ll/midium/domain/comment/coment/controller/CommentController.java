package com.ll.midium.domain.comment.coment.controller;

import com.ll.midium.domain.comment.coment.CommentCreateForm;
import com.ll.midium.domain.comment.coment.CommentForm;
import com.ll.midium.domain.comment.coment.entity.Comment;
import com.ll.midium.domain.comment.coment.service.CommentService;
import com.ll.midium.domain.post.post.entity.Post;
import com.ll.midium.domain.post.post.service.PostService;
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

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/post/{postId}/comment/{commentId}/modify")
    public String modifyComment(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId, Model model, Principal principal) {
//        Post findPost = postService.findById(id);
//        if(!findPost.getAuthor().equals(principal.getName())){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이없습니다.");
//        }
//        model.addAttribute("post", findPost);
//        return "domain/post/post/modify_form";

        Comment findComment = commentService.findById(commentId);
        if (!findComment.getAuthor().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이없습니다.");
        }
        model.addAttribute("comment", findComment);
        Post findPost = postService.findById(postId);
        model.addAttribute("post", findPost);
        return "domain/comment/comment/comment_modify_form";
    }

    @PostMapping("/post/{postId}/comment/{commentId}/modify")
    public String modifyComment(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId, CommentForm commentForm, Principal principal) {
        Comment findComment = commentService.findById(commentId);
        if (!findComment.getAuthor().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이없습니다.");
        }

        findComment.setContent(commentForm.getContent());
        commentService.save(findComment);
        return "redirect:/post" + postId;
    }

}
