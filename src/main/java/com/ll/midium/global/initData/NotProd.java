package com.ll.midium.global.initData;

import com.ll.midium.domain.post.post.entity.Post;
import com.ll.midium.domain.post.post.service.PostService;
import com.ll.midium.domain.user.user.entity.SiteUser;
import com.ll.midium.domain.user.user.service.UserService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!prod")
@Configuration
public class NotProd {
//    @Bean
//    public ApplicationRunner initNotProd(UserService userService, PostService postService) {
//
//        return args -> {
//            for (int i = 0; i < 100; i++) {
//                SiteUser user = userService.create("user" + i, "user" + i + "@naver.com", "1234");
//                Post post = new Post(user.getUsername(), "제목" + i, "내용" + i);
//                post.setPublish(true);
//                if (i % 2 == 0) {
//                    post.setPaid(true);
//                }
//                postService.savePost(post);
//            }
//        };
//    }
}
