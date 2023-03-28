package org.javall.notify.mq.sender.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.javall.notify.mq.model.AbstractMQ;

/**
 * 消息队列: 发送消息接口
 * @author ll
 */
public interface IMQSender {

    void send(AbstractMQ mq) throws JsonProcessingException;

}
