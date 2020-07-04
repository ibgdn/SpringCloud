# SpringCloud
Spring Cloud Learn Notes.



## 1 IBSpringCloud

### 1.1 Maven Project

创建 `IBSpringCloud` Project（Maven）。

### 1.2 注册中心

#### 1.2.1 创建

添加 `eureka` Module（Spring Boot）。

勾选 `Spring Cloud Discovery` `Eureka Server` 依赖。

#### 1.2.2 启动

将 `eureka`打包，命令行执行：

```
java -jar eureka-0.0.1-SNAPSHOT.jar --spring.profiles.active=eurekaA
java -jar eureka-0.0.1-SNAPSHOT.jar --spring.profiles.active=eurekaB
```

