
## 说明  

项目依赖 `lombok` ，需要在开发工具中安装lombok插件。

### 频率限制实现  

#### 测试

启动项目，然后用 Postman 请求，需要在Header中添加 `host=www.sunfeilong.com`。
 
	http://localhost:8080/fileInfo/getFileInfoList?token=token


#### 全局过滤器(所有的请求都会被过滤)

参数校验过滤器：`ParamCheckFilter`

* 校验查询参数是否包含 `tokan` 和 请求头是否包含 `host`, 同时包含才能执行下一个过滤器，不包含任意一个直接返回错误信息。

过滤器：`RateLimitGateFilter`

* 限制对每一个URL请求的频率，在内存内部实现。

#### 路由

把host以 `www.sunfeilong.` 开头的请求路由到到 `http://www.sunfeilong.com:8080`，并在请求头中添加 `source`。

	private String uri = "http://www.sunfeilong.com:8080";

    private static final Logger logger = LogManager.getLogger(RouteConfig.class);

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(predicate -> predicate.host("www.sunfeilong.*")
                        .filters(filter -> filter.addRequestHeader("source", "springcloud gateway"))
                        .uri(uri)
                )
                .build();

    }