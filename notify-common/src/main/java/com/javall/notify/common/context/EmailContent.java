package com.javall.notify.common.context;

import java.io.Serializable;

/**
 * TODO: 待补充字段
 * 邮件通知内容对象
 * @author ll
 */
public class EmailContent implements Serializable {

    private static final long serialVersionUID = 1L;

    private String from;

    private String[] to;

    private String[] cc;

    private String[] bcc;

    private String subject;

    private String text;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String... to) {
        this.to = to;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String... cc) {
        this.cc = cc;
    }

    public String[] getBcc() {
        return bcc;
    }

    public void setBcc(String... bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
