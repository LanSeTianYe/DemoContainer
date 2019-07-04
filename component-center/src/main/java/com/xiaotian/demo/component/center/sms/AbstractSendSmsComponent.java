package com.xiaotian.demo.component.center.sms;

import com.xiaotian.demo.component.center.base.ComponentKey;
import com.xiaotian.demo.component.center.enums.SmsPlatformTypeEnum;

/**
 * 抽象的发送消息组件
 */
public abstract class AbstractSendSmsComponent implements ComponentKey<SmsPlatformTypeEnum> {

    private final SmsPlatformTypeEnum smsPlatformTypeEnum;

    protected AbstractSendSmsComponent(SmsPlatformTypeEnum smsPlatformTypeEnum) {
        this.smsPlatformTypeEnum = smsPlatformTypeEnum;
    }

    protected abstract void sendSMSTo(String phone, String context);

    @Override
    public SmsPlatformTypeEnum getKey() {
        return smsPlatformTypeEnum;
    }
}
