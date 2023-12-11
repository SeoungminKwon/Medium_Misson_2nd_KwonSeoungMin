package com.ll.midium.domain.user.user.repository;

import com.ll.midium.domain.user.user.entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository< SiteUser, Long> {
}
