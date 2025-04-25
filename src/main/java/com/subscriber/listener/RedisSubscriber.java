package com.subscriber.listener;

import java.nio.charset.StandardCharsets;
import org.springframework.data.redis.connection.Message;
import org.springframework.stereotype.Component;

@Component
public class RedisSubscriber implements ChannelAwareListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String body = new String(message.getBody(), StandardCharsets.UTF_8);
        System.out.println("수신한 메시지: " + body);
    }

    @Override
    public String getSubscribeChannel() {
        return "opensearch-trigger";
    }
}
