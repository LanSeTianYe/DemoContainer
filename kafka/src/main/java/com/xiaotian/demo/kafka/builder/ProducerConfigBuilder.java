package com.xiaotian.demo.kafka.builder;


import com.xiaotian.demo.kafka.constant.KafkaConfigConstant;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Properties;

/**
 * 生产者配置信息构造器
 */
public class ProducerConfigBuilder {

    private Properties properties;

    public static ProducerConfigBuilder start() {
        ProducerConfigBuilder producerConfigBuilder = new ProducerConfigBuilder();
        producerConfigBuilder.properties = new Properties();
        producerConfigBuilder.properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfigConstant.BOOTSTRAP_SERVERS_CONFIG);
        return producerConfigBuilder;
    }


    /**
     * 集群元数据最大保留时间，超过时间将主动刷新集群信息，单位ms
     */
    public ProducerConfigBuilder withMetadataMaxAge(long metadataMaxAge) {
        this.properties.put(ProducerConfig.METADATA_MAX_AGE_CONFIG, metadataMaxAge);
        return this;
    }

    /**
     * 添加ack配置,默认值 1
     * <p>
     * 可用值[all, -1, 0, 1,...]
     * -1 代表所有, 其它表示需要确认的分区的数量，
     * 0  不等待，直接返回true
     * 1 leader节点保存成功即可
     * all 所有
     */
    public ProducerConfigBuilder withAck(int acks) {
        this.properties.put(ProducerConfig.ACKS_CONFIG, acks);
        return this;
    }


    /**
     * key 序列化类
     */
    public ProducerConfigBuilder withKeySerializedClass(Class<? extends Serializer> keySerializedClass) {
        this.properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializedClass.getCanonicalName());
        return this;
    }


    /**
     * value 序列化类
     */
    public ProducerConfigBuilder withValueSerializedClass(Class<? extends Serializer> valueSerializedClass) {
        this.properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializedClass.getCanonicalName());
        return this;
    }

    /**
     * 指定kafka服务器节点列表  host1:port1,host2:port2
     */
    public ProducerConfigBuilder withBootStrapServers(String servers) {
        this.properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        return this;
    }

    /**
     * 生产者用于缓存等待发送给服务器数据的缓冲池的大小，当生产数据过快生产者来不及发送给服务器，会阻塞 max.block.ms 时间，超时将抛出异常
     * 默认值 33554432
     */
    public ProducerConfigBuilder withBufferMemory(long bufferMemory) {
        this.properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
        return this;
    }

    /**
     * 生产者生产数据的压缩类型，可用值 none, gzip, snappy, or lz4。 默认值：none
     *
     */
    public ProducerConfigBuilder withCompressionType(String compressionType){
        this.properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, compressionType);
        return this;
    }

    /**
     * 发送失败时的重试次数，可用值[0,...,2147483647], 默认值 0
     */
    public ProducerConfigBuilder withRetries(Integer retries) {
        this.properties.put(ProducerConfig.RETRIES_CONFIG, retries);
        return this;
    }

    /**
     * 批量发数据的大小，发送给服务器的数据将包含多个分批，默认值 16384，可用值[0,...]
     */
    public ProducerConfigBuilder withBatchSize(int batchSize) {
        this.properties.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
        return this;
    }

    /**
     * 客户端Id，服务器打印日志的时候可以打印客户端ID，可以区分同一主机的不同生产者
     */
    public ProducerConfigBuilder withClientId(String clientId) {
        this.properties.put(ProducerConfig.CLIENT_ID_CONFIG, clientId);
        return this;
    }

    /**
     * 客户端连接最大空闲时间，超过将被关闭，默认值 540000，单位毫秒
     * @return
     */
    public ProducerConfigBuilder withConnectionsMaxIdleTime(long maxIdleTime) {
        this.properties.put(ProducerConfig.CONNECTIONS_MAX_IDLE_MS_CONFIG, maxIdleTime);
        return this;
    }


}

