package com.xiaotian.demo.component.center.sms;

import com.xiaotian.demo.component.center.base.AbstractComponentCenter;
import com.xiaotian.demo.component.center.enums.SmsPlatformTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class SmsSendCenter extends AbstractComponentCenter<SmsPlatformTypeEnum, AbstractSendSmsComponent> {

    public SmsSendCenter(List<AbstractSendSmsComponent> componentList) {
        super(componentList);
    }

    public void sendSms(SmsPlatformTypeEnum platform, String phone, String message) {
        AbstractSendSmsComponent component = this.getComponent(platform);
        if (component == null) {
            log.warn("not component find for key {}, in: {}", platform, this.getClass().getCanonicalName());
        } else {
            component.sendSMSTo(phone, message);
        }
    }
}
