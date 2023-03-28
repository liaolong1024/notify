package org.javall.notify.handler;

import com.javall.notify.common.context.NotifyType;
import com.javall.notify.common.context.TaskContext;
import com.javall.notify.common.exception.BizException;

/**
 * @author LiaoLong
 * @create 2023-03-28 13:54
 */
public interface Handler {

    void handle(TaskContext taskContext) throws BizException;

    NotifyType notifyType();

}
