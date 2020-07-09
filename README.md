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

勾选 `Spring Cloud routing` `Gateway` 依赖。

####  1.12.2 访问地址

```
# 请求
http://localhost:8080/get
# 实际返回以下地址的数据
http://httpbin.org/get
```