package com.ll.midium.domain.user.user.controller;

import com.ll.midium.domain.user.user.UserCreateForm;
import com.ll.midium.domain.user.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/member/join")
    public String signup(UserCreateForm userCreateForm) {
        return "domain/user/user/signup_form";
    }

    @PostMapping("/member/join")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "domain/user/user/signup_form";
        }
        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 페스워드가 일치하지 않습니다.");
            return "domain/user/user/signup_form";
        }
        try {
            userService.create(userCreateForm.getUsername(),
                    userCreateForm.getEmail(), userCreateForm.getPassword1());
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "domain/user/user/signup_form";
        } catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "domain/user/user/signup_form";
        }

        return "redirect:/";
    }

    @GetMapping("/member/login")
    private String login() {
        return "domain/user/user/login_form";
    }


}
