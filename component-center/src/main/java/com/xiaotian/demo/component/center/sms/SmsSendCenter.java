package com.xiaotian.demo.component.center.sms;

import com.xiaotian.demo.component.center.base.AbstractComponentCenter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class SmsSendCenter extends AbstractComponentCenter<AbstractSendSmsComponent> {

    public SmsSendCenter(List<AbstractSendSmsComponent> componentList) {
        super(componentList);
    }

    public void sendSms(String platform, String phone, String message) {
        AbstractSendSmsComponent component = this.getComponent(platform);
        if (component == null) {
            log.warn("not component find for key {}, in: {}", platform, this.getClass().getCanonicalName());
        } else {
            component.sendSMSTo(phone, message);
        }
    }
}
