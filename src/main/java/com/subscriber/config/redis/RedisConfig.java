package com.subscriber.config.redis;

import com.subscriber.listener.ChannelAwareListener;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory,
        List<ChannelAwareListener> listeners) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        for (ChannelAwareListener listener : listeners) {
            container.addMessageListener(listener, new ChannelTopic(listener.getSubscribeChannel()));
        }

        return container;
    }
}
