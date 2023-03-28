package org.javall.notify.mq.sender.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.javall.notify.mq.model.AbstractMQ;
import org.javall.notify.mq.sender.rabbitmq.IMQSender;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ll
 */
@Component
@Slf4j
public class RabbitMQSender implements IMQSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final String FANOUT_EXCHANGE_PREFIX = "fanout_exchange_";

    private final String TOPIC_EXCHANGE_PREFIX = "topic_exchange_";


    @Override
    public void send(AbstractMQ mq) throws JsonProcessingException {
        log.info("开始发送消息: 交换机{}, 队列名{}, 消息内容{}", mq.getExchangeName(), mq.getMQName(), mq.toMessage());
        switch (mq.getMQType()) {
            case DIRECT:
                // 使用默认交换机"", 这里是使用队列名做路由键
                rabbitTemplate.convertAndSend(mq.getMQName(), mq.toMessage());
                break;
            case BROADCAST:
                // fanout模式下不需要指定routeKEY
                rabbitTemplate.convertAndSend(FANOUT_EXCHANGE_PREFIX+mq.getMQName(), null, mq.toMessage());
                break;
            case TOPIC:
                // topic模式，注意路由键的使用
                rabbitTemplate.convertAndSend(
                        TOPIC_EXCHANGE_PREFIX + mq.getExchangeName(),
                        mq.getMQName(),
                        mq.toMessage()
                );
        }
    }
}
