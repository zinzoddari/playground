package com.subscriber.opensearch.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardDocument {

    private Long id;

    private String title;

    private String content;

    private String createdBy;

    private LocalDateTime createdDate;

    private String updateBy;

    public BoardDocument(Long id, String title, String content, String createdBy, LocalDateTime createdDate, String updateBy) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.updateBy = updateBy;
    }

    @Builder


    public static BoardDocument create(final Long id, final String title, final String content) {
        return BoardDocument.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();
    }
}
