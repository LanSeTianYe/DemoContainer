package com.xiaotian.demo.component.center.mail;

import com.xiaotian.demo.component.center.base.AbstractComponentCenter;
import com.xiaotian.demo.component.center.enums.EmailPlatformTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class MailSendCenter extends AbstractComponentCenter<EmailPlatformTypeEnum, AbstractSendMailComponent> {

    protected MailSendCenter(List<AbstractSendMailComponent> componentList) {
        super(componentList);
    }

    /**
     * 发送邮件
     *
     * @param platform 平台
     * @param email    发送到的邮箱
     * @param content  发送内容
     */
    public void send(EmailPlatformTypeEnum platform, String email, String content) {
        AbstractSendMailComponent component = getComponent(platform);
        if (component == null) {
            log.warn("not component find for key {}, in: {}", platform, this.getClass().getCanonicalName());
        } else {
            component.sendMailTo(email, content);
        }
    }
}
