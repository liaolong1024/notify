package org.javall.notify.handler.mail;

import com.javall.notify.common.context.EmailContent;
import com.javall.notify.common.context.NotifyType;
import com.javall.notify.common.context.TaskContext;
import com.javall.notify.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.javall.notify.handler.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ll
 */
@Component
@Slf4j
public class EmailHandler implements Handler {

    private final NotifyType notifyType = NotifyType.EMAIL;

    @Autowired
    private MailUtil mailUtil;

    @Override
    public void handle(TaskContext taskContext) throws BizException {
        if (taskContext.getContent() instanceof EmailContent) {
            log.info("开始发送邮件：{}", taskContext.getContent());
            EmailContent emailContent = (EmailContent) taskContext.getContent();
            mailUtil.sendSimpleMail(emailContent);
        } else {
            log.error("通知数据类型{}.content不匹配: 不是 {}", TaskContext.class.getName(), EmailContent.class.getName());
            throw new BizException("error");
        }
    }

    @Override
    public NotifyType notifyType() {
        return notifyType;
    }
}
