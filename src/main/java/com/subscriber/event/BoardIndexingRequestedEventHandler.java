package com.subscriber.event;

import com.subscriber.opensearch.indexer.BoardIndexer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoardIndexingRequestedEventHandler {

    private final BoardIndexer boardIndexer;

    @EventListener
    public void saveIndexer(final BoardIndexingRequestedEvent event) {
        boardIndexer.save(event.getBoardDocument());
    }
}
