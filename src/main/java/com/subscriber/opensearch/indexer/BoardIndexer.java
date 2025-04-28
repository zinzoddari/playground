package com.subscriber.opensearch.indexer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subscriber.opensearch.domain.BoardDocument;
import lombok.RequiredArgsConstructor;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class BoardIndexer {

    private final OpenSearchClient openSearchClient;

    private final ObjectMapper objectMapper;

    public void save(final BoardDocument boardDocument) {
        try {
            openSearchClient.index(it -> it
                    .index("boards")
                    .id(boardDocument.getId().toString())
                    .document(boardDocument));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
