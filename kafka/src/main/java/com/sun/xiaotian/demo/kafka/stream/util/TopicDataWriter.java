package com.sun.xiaotian.demo.kafka.stream.util;


/**
 * 像topic里面推送数据
 */
public interface TopicDataWriter {


    /**
     * 向指定topic推送数据
     * @param topic        topic 名字
     * @param millSeconds  时间，单位毫秒
     */
    public void producer(String topic, long millSeconds);
}

