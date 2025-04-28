package com.subscriber.listener;

import java.lang.reflect.ParameterizedType;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.subscriber.event.BoardIndexingRequestedEvent;
import com.subscriber.opensearch.domain.BoardDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.connection.Message;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisSubscriber implements ChannelAwareListener {

    private final ApplicationEventPublisher publisher;

    private final ObjectMapper objectMapper;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        final String body = new String(message.getBody(), StandardCharsets.UTF_8);

        publisher.publishEvent(BoardIndexingRequestedEvent.from(parse(body, new TypeReference<>() {
        })));
    }

    @Override
    public String getSubscribeChannel() {
        return "opensearch-trigger";
    }

    private <T> T parse(final String body, final TypeReference<T> typeReference) {
        try {
            String decodedJson = objectMapper.readValue(body, String.class); // 1단계: String 한번 풀기
            return objectMapper.readValue(decodedJson, typeReference); // 2단계: 진짜 객체로 변환
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
