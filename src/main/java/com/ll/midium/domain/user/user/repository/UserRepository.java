package com.ll.midium.domain.user.user.repository;

import com.ll.midium.domain.user.user.entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository< SiteUser, Long> {
    Optional< SiteUser > findByusername(String username);
}
