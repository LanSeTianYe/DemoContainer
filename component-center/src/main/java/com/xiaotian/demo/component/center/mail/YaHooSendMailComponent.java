package com.xiaotian.demo.component.center.mail;

import com.xiaotian.demo.component.center.enums.EmailPlatformTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 谷歌邮件发送组件
 */
@Component
@Slf4j
public class YaHooSendMailComponent extends AbstractSendMailComponent {

    public YaHooSendMailComponent() {
        super(EmailPlatformTypeEnum.YAHOO);
    }


    @Override
    public void sendMailTo(String email, String context) {
        log.info("component:{}, send mail: {}, to: {}", getClass().getCanonicalName(), context, email);
    }
}
