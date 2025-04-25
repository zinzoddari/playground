package com.playground.infra.redis.publisher;

public interface MessagePublisher<T> {

    void publish(RedisChannel channel, T object);
}
