package org.javall.notify.handler.mail;

import com.javall.notify.common.context.EmailContent;
import com.javall.notify.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.net.URL;
import java.util.Date;
import java.util.Objects;


/**
 * @author ll
 */
@Component
@Slf4j
public class MailUtil {

    private final String  MAIL_TEMPLATE_PATH = "static";

    @Autowired
    JavaMailSender mailSender;

    /**
     * 创建简单文本邮件
     */
    public SimpleMailMessage createSimpleMailMessage(EmailContent emailContent) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        BeanUtils.copyProperties(emailContent, mailMessage);
        mailMessage.setSentDate(new Date());
        return mailMessage;
    }

    /**
     * 创建可携带附件的邮件
     */
    public MimeMessage createMimeMessage(EmailContent emailContent) throws MessagingException, BizException {
        // 邮件基本信息封装
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeHelper = new MimeMessageHelper(mimeMessage, true);
        mimeHelper.setFrom(emailContent.getFrom());
        mimeHelper.setTo(emailContent.getTo());
        mimeHelper.setSubject(emailContent.getSubject());
        mimeHelper.setSentDate(new Date());

        // 获取邮件模板资源 TODO: 应当根据邮件类型来获取相应附件
        URL resource = this.getClass().getClassLoader().getResource(MAIL_TEMPLATE_PATH);
        if (Objects.isNull(resource)) {
            log.error("邮件发送资源附件 {} 不存在", MAIL_TEMPLATE_PATH);
            throw new BizException("邮件资源附件不存在");
        }
        String rootPath = resource.getFile();
        FileSystemResource cat = new FileSystemResource(rootPath + "/mail/cat.jpg");
        if (Objects.isNull(cat.getFilename())) {
            log.error("邮件发送资源附件 {} 不存在", MAIL_TEMPLATE_PATH);
            throw new BizException("邮件发送资源附件不存在");
        }

        // 添加附件 TODO: 应当根据邮件类型来获取相应模板
        mimeHelper.addAttachment(cat.getFilename(), cat);
        mimeHelper.setText("<h2 style='color:#f00;'>Hello Cat<img src='cid:p01' /></h2>", true);
        mimeHelper.addInline("p01", cat);

        return mimeMessage;
    }

    /**
     *  发送邮件simpleMailMessage
     */
    public void send(SimpleMailMessage... simpleMailMessage) {
        mailSender.send(simpleMailMessage);
    }

    /**
     * 发送邮件simpleMailMessage
     */
    public void sendSimpleMail(EmailContent emailContent) {
        SimpleMailMessage simpleMailMessage = createSimpleMailMessage(emailContent);
        mailSender.send(simpleMailMessage);
    }

    /**
     * 发送邮件MimeMessage
     */
    public void send(MimeMessage... mimeMessage) {
        mailSender.send(mimeMessage);
    }

    /**
     * 发送邮件MimeMessage
     */
    public void sendMimeMail(EmailContent emailContent) throws MessagingException, BizException {
        MimeMessage mimeMessage = createMimeMessage(emailContent);
        mailSender.send(mimeMessage);
    }
}
