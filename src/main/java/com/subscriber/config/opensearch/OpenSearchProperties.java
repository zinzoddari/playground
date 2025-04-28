package com.subscriber.config.opensearch;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
class OpenSearchProperties {

    private final String host;

    private final int port;

    private final String scheme;

    OpenSearchProperties(@Value("${opensearch.host}") final String host,
                         @Value("${opensearch.port}") final int port,
                         @Value("${opensearch.scheme}") final String scheme) {
        this.host = host;
        this.port = port;
        this.scheme = scheme;
    }
}
