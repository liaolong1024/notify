package org.javall.notify.mq.receiver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javall.notify.common.context.TaskContext;
import com.javall.notify.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.javall.notify.mq.model.TestMQ;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

/**
 *
 * Test队列的消息处理，消费逻辑不做处理，交由具体业务实现TestMQ.IMQReceiver接口定义消费逻辑
 *
 * @author ll
 */
@Component
@ConditionalOnBean(TestMQ.IMQReceiver.class)
@Slf4j
public class TestReceiver implements IMQReceiver{

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestMQ.IMQReceiver mqReceiver;

    @Override
    @RabbitListener(queues = TestMQ.MQ_NAME)
    public void receiveMsg(String msg) throws JsonProcessingException, BizException {
        log.info("消费端消费消息: {}", msg);
        mqReceiver.receive(objectMapper.readValue(msg, TaskContext.class));
    }
}
