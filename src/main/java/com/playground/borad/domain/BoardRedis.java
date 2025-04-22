package com.playground.borad.domain;

import com.playground.infra.orm.AuditedInfo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.util.ObjectUtils;

@Getter
@RedisHash(value = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRedis extends AuditedInfo {

    @Id
    private Long id;

    private String title;

    private String content;

    private String createdBy;

    private String updateBy;

    @Builder
    public BoardRedis(String title, String content, String createdBy, String updateBy) {
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
        this.updateBy = updateBy;
    }

    public static BoardRedis created(final String title, final String content, final String createdBy) {
        return BoardRedis.builder()
                .title(title)
                .content(content)
                .createdBy(createdBy)
                .build();
    }
}
