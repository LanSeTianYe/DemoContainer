package com.xiaotian.demo.component.center.sms;

import com.xiaotian.demo.component.center.enums.SmsPlatformTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * SMS2消息发送组件
 */

@Slf4j
@Component
public class SMS2SendMailComponent extends AbstractSendSmsComponent {

    public SMS2SendMailComponent() {
        super(SmsPlatformTypeEnum.SMS2);
    }

    @Override
    protected void sendSMSTo(String phone, String context) {
        log.info("component:{}, send sms: {}, to: {}", getClass().getSimpleName(), context, phone);
    }
}
