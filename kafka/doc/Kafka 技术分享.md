时间：2018/5/24 10:30:52   

参考：
  
1. [A Brief History of Kafka, LinkedIn’s Messaging Platform](https://insidebigdata.com/2016/04/28/a-brief-history-of-kafka-linkedins-messaging-platform/)
1. [kafka官网](https://kafka.apache.org/)
2. [kafka github 地址](https://github.com/apache/kafka)
3. [kafka-manager](https://github.com/yahoo/kafka-manager)
4. [Kafka深度解析](http://www.jasongj.com/2015/01/02/Kafka%E6%B7%B1%E5%BA%A6%E8%A7%A3%E6%9E%90/)
5. [Apache BookKeeper](http://bookkeeper.apache.org/)
6. [Kafka文件存储机制那些事](https://tech.meituan.com/kafka-fs-design-theory.html)
7. [为什么Kafka那么快](https://mp.weixin.qq.com/s?__biz=MzIxMjAzMDA1MQ==&mid=2648945468&idx=1&sn=b622788361b384e152080b60e5ea69a7#rd)
8. [消息队列及常见消息队列介绍](https://cloud.tencent.com/developer/article/1006035)

## 基础知识

### Kafka的诞生

2010年的时候，LinkedIn的工程团队需要重新设计LinkedIn的基础架构，为了适应不断增长的站点复杂度，把单一的应用架构迁移到面向微服务的架构。他们给不同流和队列数据开发了几种不同的自定义的数据管道。

### 简介 

当前版本: 1.1.0。

kafka是Linkedln在2011年开放的分布式消息系统，是Apache的一个子项目。从0.10版本开始，Kafka的标语已经从“一个高吞吐量、分布式的消息系统”改为“一个分布式的流平台”。

可以用来做：

 * 消息系统：作为消息队列和发布订阅系统使用。
 * 存储系统：持久化存储数据，通过ack机制确保数据被持久化到指定数量的分片上，通过指定数据保留时间，动态清除过期数据，保证磁盘空间充足。
 * 流处理系统：从一个或多个Topic拉取数据，然后进行数据处理，之后输出到一个或多个Topic。

核心API：

*  Producer API ：向kafka中推送数据。
*  Consumer API ：从kafka中拉取数据。
*  Streams API  ：把一个或多个topic中的数据，经过一系列计算，输出到另外的一个或多个topic中。
*  Connector API：从其它数据系统导入数据到kafka或者把kafka中的数据导入到其它系统。

### 核心概念和架构

* Broker：集群都多个运行的Kafka实例组成，每个运行的Kafka实例就是一个Broker。
* Topic：数据被发送到kafka的时候会指定一个类别，这个类别就是一个 `Topic` 。
* Partition（分区）：每个Topic可以由一个到多个分区组成，分区由有序的不可变的Record组成，分区数据记录在磁盘的结构化日志文件中。

	![s](https://raw.githubusercontent.com/longlongxiao/Notes/master/images/kafka/log_anatomy.png)

	* 主分区：处理分区数据的读写操作。
	* 副分区：复制主分区数据，当主分区挂掉之后副本分区会自动提升为主分区。

* Record：消息的基本单位，由 `key`、`value` 和 `timestamp`组成，每个Record有一个 `offset` 标记了Record在分区上的位置。
* 生产者：推送数据到Kafka的Topic中，需要指定对应的分区，如果没有指定分区，数据会根据 `key` 进行分区。
* 消费者：消费者从Toipc中拉取数据，消费者自己记录消费数据的偏移量，因此消费者可以通过修改偏移量达到消费旧数据或者跳过一部分消费数据的目的。

	![](https://raw.githubusercontent.com/longlongxiao/Notes/master/images/kafka/log_consumer.png)

* 消费者组：一个消费者组包含一个或多个消费者，kafka保证一个分区的数据只会向消费者组发送一次，每条数据只被消费者组中的一个消费者消费。只有一个消费者组的情况下类似于传统的消息队列，订阅topic的消费者属于不同的分组，则实现消息的订阅于通知。

### 应用场景

#### 消息系统

Kafka被当作传统消息中间件的替代品。消息中间件的使用原因有多种（从数据生产者解耦处理，缓存未处理的消息等）。与大多数消息系统相比，Kafka具有更好的吞吐量，内置的分区，多副本和容错功能，这使其成为大规模消息处理应用程序的良好解决方案。

在我们的经验中，消息的使用通常是相对较低的吞吐量，但可能需要较低的端到端延迟，并且通常需要强大的持久性保证，这些Kafka都能提供。

在这些要点中，Kafka可与传统消息系统（如ActiveMQ或RabbitMQ）媲美。

#### 网站行为跟踪

Kafka的初衷就是能够将用户行为跟踪管道重构为一组实时发布-订阅数据源。这意味着网站活动（页面浏览量，搜索或其他用户行为）将被发布到中心主题，这些中心主题是每个用户行为类型对应一个主题的。这些数据源可被订阅者获取并用于一系列的场景，包括实时处理，实时监控和加载到Hadoop或离线数据仓库系统中进行离线处理和报告。

用户行为跟踪通常是非常高的数据量，因为用户每个页面浏览的都会生成许多行为活动消息。

#### 测量

kafka经常用于运行监控数据。这涉及汇总分布式应用程序的统计数据，以产生操作运营数据的汇总数据。

#### 日志聚合

许多人使用Kafka作为日志搜集解决方案的替代品。日志搜集通常从服务器收集物理日志文件，并将它们集中放置（可能是文件服务器或HDFS），以便后续处理。kafka抽象出文件的细节，并将日志或事件数据作为消息流清晰地抽象出来。这可以为更低处理延迟提供支持，对多数据源和分布式数据消费更容易支持。与以日志为中心的系统（如Scribe或Flume）相比，Kafka性能同样出色，由于副本机制确保了更强的耐用性保，并且端到端延迟更低。

#### 流处理

许多kafka使用者处理由多个阶段组成的处理管道中的数据，其中原始输入数据从kafka主题消费，然后汇总，丰富或以其他方式转换为新主题以便进一步消费或后续处理。例如，用于推荐新闻文章的管道可以从RSS提要中抓取文章内容并将其发布到“文章”主题;进一步规范化或删除重复内容，并将清洗后的文章内容发布到新主题。最后的处理阶段可能会尝试向用户推荐这些内容。这样的管道创建实时基于各个主题数据流图。从0.10.0.0版本开始，Apache Kafka提供了一个名为Kafka Streams的轻量级，但功能强大的流处理库，可执行如上所述的数据处理。除了Kafka Streams之外，替代开源流处理工具还包括Apache Storm和Apache Samza。

#### 事件源

事件源是一种应用程序设计风格，其中状态的改变作为事件序列被记录下来。 Kafka对非常大的存储日志数据提供支持，使其成为以此风格构建的应用程序的一种优秀后端。

#### 提交日志

Kafka可以作为分布式系统的一种外部提交日志。日志有助于在节点间复制数据，并作为故障节点恢复其数据的重新同步机制。kafka日志压缩功能有助于这种使用场景。在这个场景中，Kafka类似于Apache BookKeeper。

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


## kafka 生态系统


## 可视化管理

### kafka-manager 可视化管理工具

1. 下载地址 : [https://github.com/yahoo/kafka-manager](https://github.com/yahoo/kafka-manager)
2. 用官方的方法安装会比较慢，安装好的压缩包 [下载地址](https://blog.wolfogre.com/posts/kafka-manager-download/)。
3. 下载完成之后解压，先修改配置文件的zookeeper地址，然后进入 `bin` 目录，执行 `./kafka-manager`。
4. 浏览器打开web页面，点击创建集群。 

### zookeeper 可视化管理工具 

* zk-web：[https://github.com/qiuxiafei/zk-web](https://github.com/qiuxiafei/zk-web)

## 附录
#### 常用命令

* 查看kafka日志文件 

		/home/software/kafka/kafka_2.11-1.1.0/bin/kafka-run-class.sh kafka.tools.DumpLogSegments --files /tmp/kafka-logs/topic-2p-2r-1/00000000000000000000.log  --print-data-log

#### 遇到的问题

* 文件打开数量过多(未解决)

		# 查看文件最大打开数量
		ulimit -a | grep "open files"
		# 设置文件最大打开数量
		ulimit -n 4096

