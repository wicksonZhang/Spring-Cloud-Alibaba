# Spring Cloud Alibaba Sentinel

> 代码地址：https://github.com/wicksonZhang/Spring-Cloud-Alibaba

## 基础概念

### Sentinel 解决了什么问题？

Spring Cloud Alibaba Sentinel 主要解决了服务与服务之间的熔断和限流，提高系统的 **稳定性** 和 **可靠性**，确保在异常情况下系统可以正常使用，同时防止故障扩散，以下是一个具体的应用场景说明：

假设有一个微服务架构的电子商务系统，其中包含多个服务，如用户服务、订单服务、支付服务等。在高并发情况下，如果某个服务出现异常或者因为流量过大而无法正常处理请求，这可能导致整个系统的崩溃或性能下降。

Spring Cloud Alibaba Sentinel 可以通过设置每个服务的流量阈值，当达到了服务的设定的阈值之后，直接进行流量控制，防止服务过载。



### Sentinel 是什么？

> 官网地址：https://sentinelguard.io/zh-cn/
>
> Github地址：https://github.com/alibaba/Sentinel

Spring Cloud Alibaba Sentinel是一个用于流量控制、熔断降级、系统负载保护等功能的分布式系统的开源框架。它是Alibaba开发的一个子项目，与Spring Cloud无缝集成。

Sentinel 主要功能如下：

* **实时监控：**Sentinel 提供了实时监控的统计功能可以在提供的 Dashboard 查看每个服务的请求量、错误率、响应时间等指标。
* **规则配置：** Sentinel支持通过代码或配置文件定义流量控制、熔断降级等规则，灵活适应不同场景的需求。
* **流量控制：** Sentinel可以对服务进行流量控制，限制每个服务的请求流量，防止系统过载。
* **熔断降级：** 当服务出现异常或错误率超过阈值时，Sentinel可以自动触发熔断机制，停止对该服务的请求，避免错误的传播。

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202401251600193.png" alt="image-20240125160010526" style="zoom:100%;float:left" />



### Sentinel 对比 Hystrix 

* Sentinel 是在 Hystrix 后面进行研发的，所以本质上借鉴了 Hystrix 一些优秀的点进行了进一步的开发。

| 特点 / 框架           | Spring Cloud Alibaba Sentinel | Netflix Hystrix              |
| --------------------- | ----------------------------- | ---------------------------- |
| **流量控制策略**      | 灵活、细致化                  | 相对简单                     |
| **实时监控和统计**    | 支持，提供实时监控功能        | 有监控功能，但较为基础       |
| **规则配置**          | 灵活，支持代码或配置文件配置  | 相对简单的配置               |
| **容错能力**          | 强大的熔断降级机制            | 相对较为简单的容错机制       |
| **轻量级**            | 相对较轻量级                  | 相对较重量级                 |
| **Spring Cloud 集成** | 原生支持Spring Cloud Alibaba  | 通过Spring Cloud Netflix集成 |
| **社区活跃度**        | 活跃                          | 项目处于维护模式             |



## Sentinel 安装启动

* 本次我们安装的是 `Dashboard` 控制台界面，下载地址：https://github.com/alibaba/Sentinel/releases/download/1.7.0/sentinel-dashboard-1.7.0.jar

* 启动命令如下

```cmd
E:\JavaProjects\Spring-Cloud-Alibaba\doc\03-Spring Cloud Alibaba Sentinel>java -jar sentinel-dashboard-1.7.0.jar
INFO: log output type is: file
INFO: log charset is: utf-8
INFO: log base dir is: C:\Users\minuo-java02\logs\csp\
INFO: log name use pid is: false

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.0.5.RELEASE)
 ...............
```

* 访问地址：http://localhost:8080 , 账号密码都是 sentinel

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202401251641142.png" alt="image-20240125164157091" style="zoom:100%;float:left" />



## Sentinel 具体操作

### 实现步骤

```tex
1. 创建 sentinel 服务：03-spring-cloud-alibaba-sentinel-server-2400
2. 导入 pom.xml 依赖
3. 创建 application.yml 依赖
4. 创建 SpringCloudAlibabaSentinelApplication 启动类
5. 创建 SentinelController 控制类
```

**Step-1: 创建 sentinel 服务：03-spring-cloud-alibaba-sentinel-server-2400**

### 导入 pom.xml 依赖

```xml
<dependencies>
    <!-- 引入公共依赖 -->
    <dependency>
        <groupId>cn.wickson.cloud.alibaba</groupId>
        <artifactId>01-spring-cloud-alibaba-common</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>

    <!-- Spring Cloud alibaba Nacos 注册与发现 -->
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
    </dependency>

    <!-- Spring Cloud alibaba Sentinel 服务限流于熔断 -->
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
    </dependency>

    <!-- Spring Cloud OpenFeign 服务调用依赖包 -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-openfeign</artifactId>
    </dependency>
</dependencies>
```

### 创建 application.yml 依赖

```yaml
server:
  port: 2400

spring:
  application:
    name: spring-cloud-alibaba-sentinel-server
  cloud:
    nacos:
      discovery:
        # Nacos 服务注册中心地址
        server-addr: 192.168.10.20:8001
    sentinel:
      transport:
        # 配置 Sentinel Dashboard 地址
        dashboard: localhost:8080
        # 默认端口 8719
        port: 8719

# 暴露端点
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

### 创建启动类

```java
/**
 * Spring Cloud Alibaba Sentinel 启动类
 *
 * @author ZhangZiHeng
 * @date 2024-01-25
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "cn.wickson.cloud.alibaba")
public class SpringCloudAlibabaSentinelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAlibabaSentinelApplication.class, args);
    }

}
```

### 创建控制类

```java
/**
 * sentinel 控制类
 *
 * @author ZhangZiHeng
 * @date 2024-01-25
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/sentinel")
public class SentinelController {

    @GetMapping("/service1")
    public String service1() {
        return "This is SentinelController.service1()";
    }

    @GetMapping("/service2")
    public String service2() {
        return "This is SentinelController.service2()";
    }

}
```

### 单元测试

* 当我们访问控制类接口：http://localhost:2400/sentinel/service1 ，在 Sentinel 的控制台界面会出现如下调用链路。

![image-20240125172641743](https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202401251726813.png)



## Sentinel 流量控制

在Spring Cloud Alibaba Sentinel中，流控规则用于定义对服务的流量控制策略。在下图中针对某一个接口进行流量控制的配置：

![image-20240125175715568](https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202401251757624.png)

















