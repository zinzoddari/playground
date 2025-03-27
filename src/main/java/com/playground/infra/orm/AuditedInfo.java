package com.playground.infra.orm;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditedInfo {

    @CreatedDate
    @Comment("작성일자")
    @Column(name = "created_date", columnDefinition = "timestamp", nullable = false)
    private LocalDateTime createdDate;

}
