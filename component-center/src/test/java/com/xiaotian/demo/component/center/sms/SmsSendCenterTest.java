package com.xiaotian.demo.component.center.sms;

import com.xiaotian.demo.component.center.BaseTest;
import com.xiaotian.demo.component.center.enums.SmsPlatformTypeEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SmsSendCenterTest extends BaseTest {

    @Autowired
    private SmsSendCenter smsSendCenter;
    private final Random random = new Random(33);

    @Test
    public void sendSms() throws InterruptedException {
        while (true) {
            SmsPlatformTypeEnum platformType;
            if (random.nextBoolean()) {
                platformType = SmsPlatformTypeEnum.SMS1;
            } else {
                platformType = SmsPlatformTypeEnum.SMS2;
            }
            TimeUnit.SECONDS.sleep(2);
            smsSendCenter.sendSms(platformType, "1101010101010", "hello");
        }
    }
}