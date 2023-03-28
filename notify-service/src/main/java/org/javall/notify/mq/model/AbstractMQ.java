package org.javall.notify.mq.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javall.notify.common.context.TaskContext;
import org.javall.notify.mq.constant.MQSendEnumType;

/**
 * 消息队列属性：
 *      1. 获取队名
 *      2. 获取消息订阅的模式（rabbitmq中指交换机类型）
 *      3. 获取消息体
 * @author ll
 */
public abstract class AbstractMQ {

    /** 定义和业务相关的数据 */
    protected TaskContext taskContext;

    /** 队列名应当与相应的业务相关 */
    public abstract String getMQName();

    /** RabbitMQ中的交换机名称，其他消息中间件无此概念则可不用实现 */
    public String getExchangeName() {
        return "";
    }

    public abstract MQSendEnumType getMQType();

    public abstract String toMessage() throws JsonProcessingException;

}
