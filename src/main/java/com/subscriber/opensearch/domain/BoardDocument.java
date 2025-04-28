package com.subscriber.opensearch.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardDocument {

    private Long id;

    private String title;

    private String content;

    @Builder(access = AccessLevel.PROTECTED)
    private BoardDocument(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public static BoardDocument create(final Long id, final String title, final String content) {
        return BoardDocument.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();
    }
}
