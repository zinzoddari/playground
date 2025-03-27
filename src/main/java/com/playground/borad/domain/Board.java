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
import org.springframework.util.ObjectUtils;

@Getter
@Entity
@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends AuditedInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Comment("제목")
    @Column(name = "title", columnDefinition = "varchar(32)", nullable = false)
    private String title;

    @NotNull
    @Comment("내용")
    @Column(name = "content", columnDefinition = "text", nullable = false)
    private String content;

    @NotNull
    @Comment("작성자")
    @Column(name = "createdBy", columnDefinition = "varchar(16)", nullable = false)
    private String createdBy;

    @NotNull
    @Comment("수정자")
    @Column(name = "updateBy", columnDefinition = "varchar(16)")
    private String updateBy;

    @Builder
    private Board(final Long id, final String title, final String content, final String createdBy) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
    }

    public static Board created(final String title, final String content, final String createdBy) {
        return Board.builder()
                .title(title)
                .content(content)
                .createdBy(createdBy)
                .build();
    }

    public void modify(final String title, final String content, final String updateBy) {
        updateTitle(title);
        updateContent(content);
        updateBy(updateBy);
    }

    private void updateContent(final String content) {
        if (ObjectUtils.isEmpty(content)) {
            return;
        }

        this.content = content;
    }

    private void updateTitle(final String title) {
        if (ObjectUtils.isEmpty(title)) {
            return;
        }

        this.title = title;
    }

    private void updateBy(final String updateBy) {
        assert updateBy != null;

        this.updateBy = updateBy;
    }
}
