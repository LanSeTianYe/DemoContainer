package com.xiaotian.demo.component.center.mail;

import com.xiaotian.demo.component.center.enums.EmailPlatformTypeEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import java.util.concurrent.TimeUnit;


@SpringBootTest
@RunWith(SpringRunner.class)
public class MailSendCenterTest {

    @Autowired
    private MailSendCenter mailSendCenter;

    private Random random = new Random(33);


    @Test
    public void send() throws InterruptedException {
        while (true) {
            EmailPlatformTypeEnum platformTypeEnum;
            if (random.nextBoolean()) {
                platformTypeEnum = EmailPlatformTypeEnum.YAHOO;
            } else {
                platformTypeEnum = EmailPlatformTypeEnum.GOOGLE;
            }
            TimeUnit.SECONDS.sleep(2);
            mailSendCenter.send(platformTypeEnum.name(), "test@email.com", "hello");
        }
    }
}