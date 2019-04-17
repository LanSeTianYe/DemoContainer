package com.xiaotian.demo.kafka.constant;

/**
 * @author sunfeilong   (sunfl@cloud-young.com)
 * @version V1.0
 * @date 2018年05月31日 下午11:05
 */
public interface KafkaConfigConstant {

    /**
     * kafka broker地址列表（可以只指定一个，客户端会自动获取集群中其它broker的地址）
     */
    String BOOTSTRAP_SERVERS_CONFIG = "centos_201:9092,centos_201:9092,centos_201:9092";
}
