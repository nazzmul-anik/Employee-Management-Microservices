package com.commonlib.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class AuditableEntity {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
