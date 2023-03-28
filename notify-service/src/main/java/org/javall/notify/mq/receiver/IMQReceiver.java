package org.javall.notify.mq.receiver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javall.notify.common.exception.BizException;

/**
 * @author ll
 */
public interface IMQReceiver {

    void receiveMsg(String msg) throws JsonProcessingException, BizException;

}
