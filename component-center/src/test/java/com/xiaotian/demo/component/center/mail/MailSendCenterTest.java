package com.xiaotian.demo.component.center.mail;

import com.xiaotian.demo.component.center.BaseTest;
import com.xiaotian.demo.component.center.enums.EmailPlatformTypeEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MailSendCenterTest extends BaseTest {

    @Autowired
    private MailSendCenter mailSendCenter;

    private Random random = new Random(33);


    @Test
    public void send() throws InterruptedException {
        while (true) {
            EmailPlatformTypeEnum platformType;
            if (random.nextBoolean()) {
                platformType = EmailPlatformTypeEnum.YAHOO;
            } else {
                platformType = EmailPlatformTypeEnum.GOOGLE;
            }
            TimeUnit.SECONDS.sleep(2);
            mailSendCenter.send(platformType, "test@email.com", "hello");
        }
    }
}