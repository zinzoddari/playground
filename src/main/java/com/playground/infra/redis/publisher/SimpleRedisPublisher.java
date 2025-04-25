package com.playground.infra.redis.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleRedisPublisher<T> implements MessagePublisher<T> {

    private final RedisTemplate<String, T> redisTemplate;

    private final ObjectMapper objectMapper;

    @Override
    public void publish(final RedisChannel channel, final T object) {
        redisTemplate.convertAndSend(channel.getName(), parse(object));
    }

    private String parse(final T object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("REDIS 메세지 직렬화에 실패하였습니다.", e);
            throw new RuntimeException(e);
        }
    }
}
