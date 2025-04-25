package com.subscriber.listener;

import org.springframework.data.redis.connection.MessageListener;

public interface ChannelAwareListener extends MessageListener {

    String getSubscribeChannel();
}
