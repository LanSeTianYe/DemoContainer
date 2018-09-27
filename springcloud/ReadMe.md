## 说明  

Spring Cloud 项目Demo

### 端口管理  

* springcloud-eureka-center: `[9000 - 9009)`

	注册中心，通过指定不同的 `profile` 启动集群。

* springcloud-eureka-server: `[9010 - 9020)`
	
	服务提供者，向注册中心注册服务。
* springcloud-eureka-client: `[9020 - 9030)`

	服务消费者，从注册中心获取服务列表，根据负载均衡策略调用服务接口。也可以是服务提供者。