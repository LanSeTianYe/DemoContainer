package com.sun.xiaotain.demo.springcloud.gateway.filter.ratelimit;


import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors
public class RateLimitConfig implements Serializable {

    private static final long serialVersionUID = -404809163555956377L;

    //时间间隔
    private long interval;

    //允许访问的次数
    private int allowTimes;
}
