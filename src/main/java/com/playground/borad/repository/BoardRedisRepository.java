package com.playground.borad.repository;

import com.playground.borad.domain.Board;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardRedisRepository implements BoardCacheRepository {

    private final String BOARD_KEY_PREFIX = "board:";

    private final RedisTemplate<String, Board> redisTemplate;

    @Override
    public void save(final Long id, final Board board) {
        redisTemplate.opsForValue().set(generateKey(id), board);
    }

    @Override
    public Optional<Board> get(final Long id) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(generateKey(id)));
    }

    private String generateKey(final Long id) {
        return BOARD_KEY_PREFIX + id;
    }
}
