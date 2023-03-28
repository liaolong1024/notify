package com.javall.notify.common.context;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.EnumSet;

/**
 * 通知任务传递时的上下文，包含通知内容等信息
 * @author ll
 */
@Setter
@Getter
public class TaskContext implements Serializable {

    private static final long serialVersionUID = 1L;

    // 消息Id
    private Long messageId;

    // 业务Id
    private Long businessId;

    // 消息通知方式类型
    private NotifyType notifyType;

    // 通知内容
    private EmailContent content;

}
