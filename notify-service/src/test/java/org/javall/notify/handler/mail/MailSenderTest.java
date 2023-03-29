package org.javall.notify.handler.mail;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javall.notify.common.context.EmailContent;
import com.javall.notify.common.context.TaskContext;
import com.javall.notify.common.exception.BizException;
import org.javall.notify.mq.model.TestMQ;
import org.javall.notify.mq.sender.rabbitmq.IMQSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;

/**
 * @author ll
 */
@SpringBootTest
public class MailSenderTest {

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    IMQSender imqSender;

    private final String from = "xxx@163.com";

    private final String to = "xxx@qq.com";

    private final String subject = "Hello";

    private final String text = "Hello World!";
    /**
     * 简单邮件发送
     */
    @Test
    void testMailA() {
        // 1.创建要发送的邮件对象
        EmailContent emailContent = new EmailContent();
        emailContent.setFrom(from); // 发件人, 如果未指定，默认使用配置文件中的发件人
        emailContent.setTo(to); // 收件人（ 该角色可以知道收件人、抄送人， 不知道密送人）
        // emailInfo.setCc(""); // 抄送人 （该角色可以知道收件人、抄送人， 不知道密送人）
        // emailInfo.setBcc(""); // 密送人 （该角色可以知道邮件的收件人、抄送人，不知道其他密送人）
        emailContent.setSubject(subject); // 邮件主题
        emailContent.setText(text); // 邮件内容
        // 2. 发送
        mailUtil.sendSimpleMail(emailContent);
    }

    /**
     * 复杂邮件发送 HTML, 图片，附件
     */
    @Test
    void testMailB() throws MessagingException, BizException {
        // 1. 创建邮件对象
        EmailContent emailContent = new EmailContent();

        // 2. 设置基本信息
        // multipart为true, 即支持发送附件图片等
        emailContent.setFrom(from);
        emailContent.setTo(to);
        // emailInfo.setCc();
        // emailInfo.setBcc();
        emailContent.setSubject(subject);

        // 4. 发送
        mailUtil.sendMimeMail(emailContent);
    }


    /**
     * 测试通过经由RabbitMQ发送邮件
     */
    @Test
    void testMailC() throws JsonProcessingException {
        TaskContext taskContext = new TaskContext();
        EmailContent emailContent = new EmailContent();
        emailContent.setFrom(from);
        emailContent.setTo(to);
        emailContent.setSubject(subject);
        emailContent.setText(text);
        taskContext.setContent(emailContent);
        imqSender.send(new TestMQ(taskContext));
    }

}
