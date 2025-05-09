package com.subscriber.config.opensearch;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHost;
import org.opensearch.client.RestClient;
import org.opensearch.client.json.jackson.JacksonJsonpMapper;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.transport.rest_client.RestClientTransport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
class OpenSearchConfig {

    private final OpenSearchProperties openSearchProperties;
    private final ObjectMapper objectMapper;

    @Bean
    public OpenSearchClient openSearchClient() {
        final RestClient restClient = RestClient.builder(
                new HttpHost(
                        openSearchProperties.getHost(),
                        openSearchProperties.getPort(),
                        openSearchProperties.getScheme()
                )
        ).build();

        final RestClientTransport transport = new RestClientTransport(
                restClient,
                new JacksonJsonpMapper(objectMapper)
        );

        return new OpenSearchClient(transport);
    }
}
