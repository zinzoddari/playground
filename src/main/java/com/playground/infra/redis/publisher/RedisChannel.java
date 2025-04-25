package com.playground.infra.redis.publisher;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RedisChannel {

    OPEN_SEARCH("opensearch-trigger");

    private final String name;
}
