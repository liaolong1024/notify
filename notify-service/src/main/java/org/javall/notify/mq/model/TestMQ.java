package org.javall.notify.mq.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javall.notify.common.context.TaskContext;
import com.javall.notify.common.exception.BizException;
import lombok.Getter;
import lombok.Setter;
import org.javall.notify.mq.constant.MQSendEnumType;

/**
 * 队列与业务相关，本队列是测试用队列
 *
 * @author ll
 */
@Setter
@Getter
public class TestMQ extends AbstractMQ{

    public static final String MQ_NAME = "test";

    private final String EXCHANGE_NAME = "test";

    public TestMQ(TaskContext taskContext) {
        this.taskContext = taskContext;
    }

    @Override
    public String getMQName() {
        return MQ_NAME;
    }

    @Override
    public String getExchangeName() {
        return EXCHANGE_NAME;
    }

    @Override
    public MQSendEnumType getMQType() {
        return MQSendEnumType.DIRECT;
    }

    @Override
    public String toMessage() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(taskContext);
    }

    /** 消费侧实现该接口定义消息处理逻辑即可消费TestMQ中的消息 */
    public interface IMQReceiver {
        void receive(TaskContext context) throws BizException;
    }
}
