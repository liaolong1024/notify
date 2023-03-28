package org.javall.notify.mq.constant;

/**
 * @author ll
 */
public enum MQSendEnumType {
    /** 点对点 , RabbitMQ中的Direct类型交换机*/
    DIRECT,
    /** 广播模式, RabbitMQ中的fanout类型交换机 */
    BROADCAST,
    /** 主题模式，RabbitMQ中的topic类型交换机 */
    TOPIC
}
