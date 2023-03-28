package org.javall.notify.mq.receiver;

import com.javall.notify.common.context.TaskContext;
import com.javall.notify.common.exception.BizException;
import org.javall.notify.handler.mail.EmailHandler;
import org.javall.notify.mq.model.TestMQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ll
 */
@Component
public class TestMQReceiverImpl implements TestMQ.IMQReceiver {

    @Autowired
    EmailHandler emailHandler;

    @Override
    public void receive(TaskContext context) throws BizException {
        emailHandler.handle(context);
    }
}
