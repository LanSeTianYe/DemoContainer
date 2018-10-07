## 说明  

Spring Cloud 项目Demo

### 端口管理  

* springcloud-eureka-center: `[9000 - 9009)`

	注册中心，通过指定不同的 `profile` 启动集群。

* springcloud-eureka-server: `[9010 - 9020)`
	
	服务提供者，向注册中心注册服务。
* springcloud-eureka-client: `[9020 - 9030)`

	服务消费者，从注册中心获取服务列表，根据负载均衡策略调用服务接口。也可以是服务提供者。
* springcloud-config-server: `[9030 - 9040)`

	配置中心，负责向不同项目分发配置信息。

* springcloud-config-client: `[9040 - 9050)`

	实际项目，从配置中心读取配置信息。

* springcloud-bus: `[9050 - 9060)`

	消息总线，协调为服务组件之间通信。

* springcloud-hystrix: `[9060 - 9070)`

	熔断器Demo。

* springcloud-hystrix-dashboard: `[9070 - 9080)`

	熔断器统计信息图形面板。[地址](http://localhost:9070/hystrix)

* springcloud-hystrix-turbine: `[9080 - 9090)`

	熔断器信息聚合器，聚合熔断器统计信息。[http://localhost:9080/turbine.stream](http://localhost:9080/turbine.stream)