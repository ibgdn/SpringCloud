# SpringCloud
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
