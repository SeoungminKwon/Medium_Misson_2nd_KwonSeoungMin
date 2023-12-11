package com.ll.midium.domain.base.base.entity;

import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {
    private LocalDateTime createdDate;
    private String createdAuthor;
    private LocalDateTime modifiedDate;
    private String modifiedAuthor;

}
