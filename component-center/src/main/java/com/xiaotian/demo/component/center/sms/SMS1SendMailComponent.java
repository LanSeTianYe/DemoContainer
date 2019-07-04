package com.xiaotian.demo.component.center.sms;

import com.xiaotian.demo.component.center.enums.SmsPlatformTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * SMS1消息发送组件
 */

@Slf4j
@Component
public class SMS1SendMailComponent extends AbstractSendSmsComponent {

    public SMS1SendMailComponent() {
        super(SmsPlatformTypeEnum.SMS1);
    }

    @Override
    protected void sendSMSTo(String phone, String context) {
        log.info("component:{}, send sms: {}, to: {}", getClass().getCanonicalName(), context, phone);
    }
}
