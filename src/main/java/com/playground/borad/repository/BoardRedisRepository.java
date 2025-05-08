package com.playground.borad.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.playground.borad.domain.Board;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BoardRedisRepository implements BoardCacheRepository {

    private final String BOARD_KEY_PREFIX = "board:";

    private final RedisTemplate<String, Board> redisTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void save(final Long id, final Board board) {
        redisTemplate.opsForValue().set(generateKey(id), board);
    }

    @Override
    public Optional<Board> get(final Long id) {
        Object value = redisTemplate.opsForValue().get(generateKey(id));

        if (ObjectUtils.isEmpty(value)) {
            return Optional.empty();
        }

        return Optional.ofNullable(objectMapper.convertValue(value, Board.class));
    }

    private String generateKey(final Long id) {
        return BOARD_KEY_PREFIX + id;
    }

    private String parse(final Board object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("REDIS 메세지 직렬화에 실패하였습니다.", e);
            throw new RuntimeException(e);
        }
    }
}
