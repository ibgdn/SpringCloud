# Spring Cloud
Spring Cloud Learn Notes.



## 1 IBSpringCloud

### 1.1 Maven Project

创建 `IBSpringCloud` Project（Maven）。



### 1.2 eureka 服务注册中心

#### 1.2.1 创建

添加 `eureka` Module（Spring Boot）。

勾选 `Spring Cloud Discovery`  `Eureka Server` 依赖。

#### 1.2.2 集群配置及启动

##### 1.2.2.1 配置相关

- 配置文件路径

```
IBSpringCloud/eureka/src/main/resources/application-eurekaA.properties
IBSpringCloud/eureka/src/main/resources/application-eurekaB.properties
```
生产环境需要开启自我保护机制：

```properties
eureka.server.enable-self-preservation=true
```

- 端口号

  基本 1110，集群 1111、1112。

##### 1.2.2.2 启动命令

将 `eureka`打包，命令行执行：

```
java -jar eureka-0.0.1-SNAPSHOT.jar --spring.profiles.active=eurekaA
java -jar eureka-0.0.1-SNAPSHOT.jar --spring.profiles.active=eurekaB
```



### 1.3 provider 服务注册

#### 1.3.1 创建

添加 `provider` Module（Spring Boot）。

勾选 `Web` `Spring Web` 、`Spring Cloud Discovery` `Eureka Discovery Client` 依赖。

#### 1.3.2 集群配置及启动

##### 1.3.2.1 配置相关

- 端口号

  基本 1120，集群 1121、1122。

##### 1.3.2.2 启动命令

将 `provider` 打包，注册到单实例 Eureka，命令行执行：

```
java -jar provider-0.0.1-SNAPSHOT.jar --server.port=1121
java -jar provider-0.0.1-SNAPSHOT.jar --server.port=1122
```

如果需要注册 provider 多实例到 eureka，修改配置文件`IBSpringCloud/provider/src/main/resources/application.properties`，添加注册地址：

```properties
# 服务注册地址
eureka.client.service-url.defaultZone=http://localhost:1110/eureka
```



### 1.4 consumer 服务消费

#### 1.4.1 创建

添加 `consumer` Module（Spring Boot）。

勾选 `Web` `Spring Web` 、`Spring Cloud Discovery` `Eureka Discovery Client` 依赖。

#### 1.4.2 访问地址

```
# 静态地址
http://localhost:1130/static/consumer
# 动态地址
http://localhost:1130/dynamic/consumer
# 动态线性负载均衡地址
http://localhost:1130/dynamic/balance/consumer
# RestTemplate 动态线性负载均衡
http://localhost:1130/restTemplate/consumer
# RestTemplate 结合  动态负载均衡
http://localhost:1130/loadBalanced/consumer

# RestTemplate Get 请求
http://localhost:1130/restTemplateGet

# RestTemplate Post Key Value 请求
http://localhost:1130/restTemplatePostKeyValue
# RestTemplate Post Json 请求
http://localhost:1130/restTemplatePostJson
# RestTemplate Post Location 请求
http://localhost:1130/restTemplatePostLocation

# RestTemplate Put Key Value 请求
http://localhost:1130/restTemplatePutKeyValue
# RestTemplate Post Json 请求
http://localhost:1130/restTemplatePutJson

# RestTemplate Delete Key Value 请求
http://localhost:1130/restTemplateDeleteKeyValue
# RestTemplate Delete PathVariable 请求
http://localhost:1130/restTemplateDeletePathVariable
```



### 1.5 commons 通用组件

#### 1.5.1 创建

添加 commons Module（Maven）。



### 1.6 openfeign 请求简化方案

#### 1.6.1 创建

添加 `openfeign` Module（Spring Boot）。

勾选 `Web` `Spring Web` 、`Spring Cloud Discovery` `Eureka Discovery Client` 、`Spring Cloud Routing` `OpenFeign`依赖。

####  1.6.2 访问地址
```
# OpenFeign 测试 Provider Get 接口地址
http://localhost:1140/OpenFeignControllerGet

# OpenFeign 测试 Provider Get、Post、Delete 接口地址
http://localhost:1140/OpenFeignControllerOthers
```



### 1.7 controller-api Open Feign 继承特性

#### 1.7.1 创建

添加 `controller-api` Module（Maven）。

添加 `spring-boot-starter-web ` `commons`（之前创建）依赖。

再次访问 1.6.2 中的地址，获取相同结果。



### 1.8 silience4j-demo Silience4j 测试类

#### 1.8.1 创建

添加 `silience4j-demo` Module（Maven）。

pom.xml 添加依赖。

```xml
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
        <!-- 断路器 -->
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-circuitbreaker</artifactId>
            <version>0.13.2</version>
        </dependency>
        <!-- 限流 -->
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-ratelimiter</artifactId>
            <version>0.13.2</version>
        </dependency>
        <!-- 重试 -->
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-retry</artifactId>
            <version>0.13.2</version>
        </dependency>
```



### 1.9 silience4j Silience4j 容错解决方案

#### 1.9.1 创建

添加 `silience4j` Module（Spring Boot）。

勾选 `Web` `Spring Web` 、`Spring Cloud Discovery` `Eureka Discovery Client` 依赖。

####  1.9.2 访问地址

```
# Silience4j 测试 ReTry 接口地址。
# 访问后，浏览器会提示除零操作， Provider 重试5次（application.yml 文件配置），后台输出错误日志5次。
http://localhost:1150/resilience4jReTry

# Silience4j 测试 CircuitBreaker 接口地址。
http://localhost:1150/resilience4jCircuitBreaker

# Silience4j 测试 CircuitBreaker 接口地址。
http://localhost:1150/resilience4jRateLimiter
```



### 1.10 micrometer Prometheus 监控信息

#### 1.10.1 创建

添加 `micrometer` Module（Spring Boot）。

勾选 `Web` `Spring Web` 、`Ops` `Spring Boot Actuator` 依赖。

####  1.10.2 Prometheus

配置 Prometheus 监控服务及数据。

####  1.10.3 Grafana

配置 Grafana 监控指标展示工具。



### 1.11 zuul Zuul 网关

#### 1.11.1 创建

添加 `zuul` Module（Spring Boot）。

勾选 `Web` `Spring Web` 、`Spring Cloud Discovery` `Eureka Discovery Client` 、`Spring Cloud routing` `Zuul[Maintenance]` 依赖。

####  1.11.2 访问地址

```
# Zuul 代理 Provider 访问接口
http://localhost:1170/provider/provider

# Zuul 代理 Provider 修改访问地址
http://localhost:1170/zrp/provider

# Zuul 添加过滤器拦截之后只有添加 `username` 和 `password` 参数，同时参数正确的请求才可以正常访问
http://localhost:1170/zrp/provider?username=user&password=123456
```



### 1.12 gateway Gateway 网关

#### 1.12.1 创建

添加 `gateway` Module（Spring Boot）。

勾选 `Spring Cloud routing` `Gateway` 、`Spring Cloud Discovery` `Eureka Discovery Client`依赖。

####  1.12.2 访问地址

```
# 请求
http://localhost:8080/get
# 实际返回以下地址的数据
http://httpbin.org/get

# GateWay 注册到 Eureka 后 Provider 都变成了大写字母
http://localhost:1180/PROVIDER/provider

# GateWay predicates 转发规则
http://localhost:1180/get
```



### 1.13 configserver Config Server 系统配置管理服务端

#### 1.13.1 创建

添加 `configserver` Module（Spring Boot）。

勾选  `Web` `Spring Web` 、 `Spring Cloud Config` `Config Server`  依赖。

####  1.13.2 访问地址

```
# 读取配置文件信息(Json)
http://localhost:1190/client1/dev/master
http://localhost:1190/client1/dev/

# 读取配置文件信息(字符串)
http://localhost:1190/client1-dev.properties
http://localhost:1190/client1-dev.yml
```



### 1.14 configclient Config Client 系统配置管理客户端

#### 1.14.1 创建

添加 `configclient` Module（Spring Boot）。

勾选  `Web` `Spring Web` 、 `Spring Cloud Config` `Config Client`  依赖。

####  1.14.2 访问地址

```
# 读取配置文件信息
http://localhost:1200/configClient

# 调用 serverclient post 请求的接口，就会重新获取配置数据，刷新配置信息
http://localhost:1200/actuator/refresh

# 配置 Spring Cloud Bus 调用 serverclient post 请求的接口，就会重新获取配置数据，刷新全部服务配置信息
http://localhost:1200/actuator/refresh-bus

# 调用 serverclient post 请求的接口配合 instance-idj，就会重新获取配置数据，刷新单个服务配置信息
http://localhost:1200/actuator/refresh-bus/client1:1201
```



### 1.15 stream Stream 构建消息驱动

#### 1.15.1 创建

添加 `stream` Module（Spring Boot）。

勾选  `Web` `Spring Web` 、 `Messaging` `Spring for RabbitMQ`、`Spring Cloud Messaging` `Cloud Stream`  依赖。

####  1.15.2 RabbitMQ 发送消息

Queues =》 Publish message =》 Payload 填写发送的 message

#### 1.15.2 访问地址

```
# 自定义消息通道
http://localhost:1210/sendMessage
```

#### 1.15.3 消息分组

相同或不同地址的消息，被多实例交替随机消费。

```
# stream 启动多实例（消息分组）
java -jar stream-0.0.1-SNAPSHOT.jar --server.port=1211
java -jar stream-0.0.1-SNAPSHOT.jar --server.port=1212

# 访问自定义消息地址
http://localhost:1211/sendMessage
```

#### 1.15.4 消息分区

相同特征的消息始终被同一消费者消费。

```
# stream 启动多实例（消息分区）
java -jar stream-0.0.1-SNAPSHOT.jar --server.port=1211 --spring.cloud.stream.instance-index=1
java -jar stream-0.0.1-SNAPSHOT.jar --server.port=1212 --spring.cloud.stream.instance-index=2

# 访问自定义消息地址
http://localhost:1211/sendMessage
```

#### 1.15.5 消息定时

需要 Docker 容器（或者服务器）安装插件。

rabbitmq-delayed-message-exchange[下载地址](https://github.com/rabbitmq/rabbitmq-delayed-message-exchange/releases/tag/v3.8.0)。

```
# 访问延迟消息地址
http://localhost:1210/sendDelayMessage
```



### 1.16 sleuth Sleuth 构建消息驱动

#### 1.16.1 创建

添加 `sleuth` Module（Spring Boot）。

勾选  `Web` `Spring Web` 、`Spring Cloud Tracing` `Sleuth` 依赖。

####  1.16.2 访问地址

```
# 简单调用接口
http://localhost:1220/sleuth

# 调用链调用接口
http://localhost:1220/sleuthRestTemplate

# 异步任务调用接口
http://localhost:1220/sleuthAsync
```



### 1.17 zipkin01/02 Zipkin 分布式追踪

#### 1.17.1 创建

添加 `zipkin01/02` Module（Spring Boot）。

勾选  `Web` `Spring Web` 、`Messaging` `Spring for RabbitMQ`、`Spring Cloud Tracing` `Sleuth`、`Spring Cloud Tracing` `Zipkin Client`、`Spring Cloud Messaging` `Cloud Stream` 依赖。

需要启动 RabbitMQ、ElasticSearch 和 Zipkin。

####  1.17.2 访问地址

```
# 调用 zipkin02 的接口
http://localhost:1240/zipkin02
```



### 1.18 nacos Nacos Alibaba 服务配置

#### 1.18.1 创建

添加 `nacos` Module（Spring Boot 2.8）。

勾选  `Web` `Spring Web` 、`Alibaba` `Nacos Configuration` 依赖。

####  1.18.2 访问地址

```
# 调用 nacos 的接口，获取配置文件数据
http://localhost:1250/nacos

# nacos 作为注册中心，需要启动两个实例
java -jar nacosregister-0.0.1-SNAPSHOT.jar --server.port=1261
java -jar nacosregister-0.0.1-SNAPSHOT.jar --server.port=1262
```



### 1.19 nacosregister Nacos Alibaba 服务注册

#### 1.19.1 创建

添加 `nacosregister` Module（Spring Boot 2.8）。

勾选  `Web` `Spring Web` 、`Alibaba` `Nacos Service Discovery` 依赖。

Nacos 作为注册中心。

```
# nacos 作为注册中心，需要启动两个实例
java -jar nacosregister-0.0.1-SNAPSHOT.jar --server.port=1261
java -jar nacosregister-0.0.1-SNAPSHOT.jar --server.port=1262
```

查看 Nacos 控制台的【服务管理】=》【服务列表】，可以看到多个实例。



### 1.20 nacosconsumer Nacos Alibaba 服务注册

#### 1.20.1 创建

添加 `nacosconsumer` Module（Spring Boot 2.8）。

勾选  `Web` `Spring Web` 、`Alibaba` `Nacos Service Discovery` 依赖。

Nacos 作为注册中心。

查看 Nacos 控制台的【服务管理】=》【服务列表】，可以看到多个实例。

#### 1.20.2 访问地址

```
# 调用 nacos 消费者的接口
http://localhost:1270/nacosConsumer
```



### 1.21 sentinel Sentinel Alibaba 控制台

#### 1.21.1 创建

添加 `sentinel` Module（Spring Boot 2.8）。

勾选  `Web` `Spring Web` 、`Alibaba` `Sentinel` 依赖。

#### 1.21.2 访问地址

```
# 调用 sentinel 的接口
http://localhost:1280/sentinel
```

