package com.subscriber.event;


import com.subscriber.opensearch.domain.BoardDocument;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardIndexingRequestedEvent {

    private final BoardDocument boardDocument;

    public static BoardIndexingRequestedEvent from(final BoardDocument boardDocument) {
        return new BoardIndexingRequestedEvent(boardDocument);
    }
}
