package com.xiaotian.demo.component.center.mail;

import com.xiaotian.demo.component.center.base.ComponentKey;
import com.xiaotian.demo.component.center.enums.EmailPlatformTypeEnum;

/**
 * 抽象的发送邮件组件
 */
public abstract class AbstractSendMailComponent implements ComponentKey<EmailPlatformTypeEnum> {

    private final EmailPlatformTypeEnum platformTypeEnum;

    protected AbstractSendMailComponent(EmailPlatformTypeEnum platformTypeEnum) {
        this.platformTypeEnum = platformTypeEnum;
    }

    public abstract void sendMailTo(String email, String context);

    @Override
    public EmailPlatformTypeEnum getKey() {
        return platformTypeEnum;
    }
}
