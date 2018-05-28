时间：2018/5/24 10:30:52   

参考：  

1. [kafka官网](https://kafka.apache.org/)
2. [kafka github 地址](https://github.com/apache/kafka)
3. [kafka-manager](https://github.com/yahoo/kafka-manager)

## 基础知识

### 简介 

kafka是Linkedln开放的分布式消息系统，是Apache的一个子项目。从0.9版本开始，Kafka的标语已经从“一个高吞吐量、分布式的消息系统”改为“一个分布式的流平台” 。

当前版本: 1.1.0。

可以用来做：

* 消息系统
* 数据存储
	* 数据过期时间 
* 流处理

### 核心概念

* topic
* borker

### 背景介绍
### 是什么
### 解决什么问题
### 应用场景

* 消息系统：基于消费者分组实现，topic中的数据会发送到所有的分组。分组中的消费者均匀消费topic中的数据，每条数据只会发送给一个消费者。

	* 消息队列：一个分组。
	* 发布订阅：多个分组。

## 内部原理

### 分片数据存储原理

### 集群架构

* 副本机制：通常情况下，分区的数量远大于节点的数量，从而确保每个节点上都有均匀数量的主分区。
* 节点存活条件：
	* 节点可以和Zookeeper进行通信。
	* 如果一个节点时另一个节点的副本分区，那么该节点能复制主节点的数据，并且进度不能落后太多。
* 提交机制：主分区和所有副本分区都完成消息的写入才返回写入成功。  
* 容错机制：主分片挂掉之后，副本分片会被提升为主分片。kafka此时副本分片的数量减少一个，kafka不会动态的再其它节点创建一个副本分区而是等待挂掉的节点重新连接。

### 为什么那么快？

* 零复制：把数据读取到内核中，直接从内核中发送到网络。
* 消费者采用 pull 模式，服务端不需要记录消息的消费状态，从而提升服务端执行效率。任何时刻，一个分区的数据只会被一个消费者消费。消费这需要自己记录当前消费的偏移。缺点，客户端不知道服务端有没有数据需要轮询，可以使用阻塞请求（当数据量达到一定的数值时服务端再返回）。

## 代码展示

### 分布式队列

### 流处理

### Connector

 

## 可视化管理

### kafka-manager 可视化管理工具

1. 下载地址 : [https://github.com/yahoo/kafka-manager](https://github.com/yahoo/kafka-manager)
2. 用官方的方法安装会比较慢，安装好的压缩包 [下载地址](https://blog.wolfogre.com/posts/kafka-manager-download/)。
3. 下载完成之后解压，先修改配置文件的zookeeper地址，然后进入 `bin` 目录，执行 `./kafka-manager`。
4. 浏览器打开web页面，点击创建集群。（zookeeper地址要和kafka配置文件里面的路径一样）。 

### zookeeper 可视化管理工具 

* zk-web：[https://github.com/qiuxiafei/zk-web](https://github.com/qiuxiafei/zk-web)

### 查看日志文件 

	/home/software/kafka/kafka_2.11-1.1.0/bin/kafka-run-class.sh kafka.tools.DumpLogSegments --files /tmp/kafka-logs/topic-2p-2r-1/00000000000000000000.log  --print-data-log
