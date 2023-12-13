package com.ll.midium.domain.home.home.controller;

import com.ll.midium.domain.post.post.entity.Post;
import com.ll.midium.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostService postService;

    @GetMapping("/")
    public String showMain(Model model) {

        List< Post > posters = postService.get30Posters();
        model.addAttribute("posters", posters);
        return "redirect:/post/list";
    }


}
