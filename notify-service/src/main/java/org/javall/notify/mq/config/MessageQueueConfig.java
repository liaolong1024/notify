package org.javall.notify.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ll
 */
@Configuration
public class MessageQueueConfig {

    public static final String MQ_NAME = "test";

    private final String EXCHANGE_NAME = "test";

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue queue() {
        return new Queue(MQ_NAME, false);
    }

    @Bean
    public Binding binding(@Qualifier("directExchange") DirectExchange directExchange, @Qualifier("queue") Queue queue) {
        return BindingBuilder.bind(queue).to(directExchange).with(queue.getName());
    }

}
