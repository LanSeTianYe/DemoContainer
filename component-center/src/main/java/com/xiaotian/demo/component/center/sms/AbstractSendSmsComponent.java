package com.xiaotian.demo.component.center.sms;

import com.xiaotian.demo.component.center.base.ComponentKey;
import com.xiaotian.demo.component.center.enums.SmsPlatformTypeEnum;

/**
 * 抽象的发送消息组件
 */
public abstract class AbstractSendSmsComponent implements ComponentKey {

    private final SmsPlatformTypeEnum smsPlatformTypeEnum;

    protected AbstractSendSmsComponent(SmsPlatformTypeEnum smsPlatformTypeEnum) {
        this.smsPlatformTypeEnum = smsPlatformTypeEnum;
    }

    protected abstract void sendSMSTo(String phone, String context);

    @Override
    public String getKey() {
        return smsPlatformTypeEnum.name().toLowerCase();
    }
}
