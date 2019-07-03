时间：2019/6/29 8:18:00  

## 简介  

基于 SpringBoot 实现的动态数据源，项目配置两个数据源进行切换。

需要两个数据库 `test1` `test2`，数据库脚本在 [./sql](./sql) 目录。

`DataSourceNameInterceptor` 拦截请求的参数 `datasource_name` 请求的时候传 `test1` 或 `test2` 即可看到切换效果。

	/persons?datasource_name=test1
	/persons?datasource_name=test2

其它，一切都在代码里了。