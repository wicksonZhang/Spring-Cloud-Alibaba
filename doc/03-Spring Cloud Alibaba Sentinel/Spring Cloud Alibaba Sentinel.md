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

如下是文字版描述

* **资源名：**唯一名称，默认请求路径；
* **针对来源：**Sentinel 可以针对调用者进行限流，填写微服务名，指定对哪个微服务进行限流 ，默认`default`(不区分来源，全部限制)；
* **阈值类型 / 单机阈值：**
  * **QPS：**每秒钟的请求数量，当调用接口的QPS达到阈值的时候，进行限流；
  * **线程数：**当调用接口的线程数达到阈值的时候，进行限流；

* **是否集群：**不需要集群
* **流控模式：**
  * **直接：**接口达到限流条件时，直接限流；
  * **关联：**当关联的资源达到阈值时，就限流自己；
  * **链路：**只记录指定链路上的流量 (指定资源从入口资源进来的流量，如果达到阈值，就进行限流)【api 级别的针对来源】；
* **流控效果：**
  * **快速失败：**直接失败，抛异常；
  * **Warm Up：**根据codeFactor（冷加载因子，默认为3）的值，即请求 QPS 从阈值 / codeFactor，经过预热时长，逐渐升至设定的QPS阈值；
  * **排队等待：**匀速排队，让请求以匀速的速度通过，阈值类型必须设置为QPS,否则无效；

### QPS-直接-快速失败

**QPS-直接-快速失败**：当访问接口 `/sentinel/service1` 当每秒钟的请求流量大于 1 之后直接快速失败

* **阈值类型：**QPS
* **流控模式：**直接
* **流控效果：**快速失败

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202401260951256.png" alt="image-20240126095102202" style="zoom:100%;float:left" />

![image-20240126095831425](https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202401260958464.png)

* **测试结果**

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202401260959018.gif" alt="动画" style="zoom:100%;float:left" />



### 线程数-直接-快速失败

**线程数-直接-快速失败**：当访问接口 `/sentinel/service1` 当每秒钟的请求线程数大于 1 之后直接快速失败

* **阈值类型：**QPS
* **流控模式：**直接
* **流控效果：**快速失败

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202401261020523.png" alt="image-20240126102018487" style="zoom:100%;float:left" />

![image-20240126102101376](https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202401261021413.png)

**测试结果**

* 修改 `/sentinel/service1` 接口

```java
    @GetMapping("/service1")
    public String service1() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return "This is SentinelController.service1()";
    }
```

* 测试结果

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202401261035873.gif" alt="动画" style="zoom:100%;float:left" />



### QPS-关联-快速失败

**QPS-关联 -快速失败** ：当访问关联资源 `/sentinel/service2` 当每秒钟的请求线程数大于 1 时，直接限流 `sentinel` 的访问地址。

* **阈值类型：**QPS
* **流控模式：**关联
* **流控效果：**快速失败

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202401261055406.png" alt="image-20240126105511367" style="zoom:100%;float:left" />

* 测试结果，当 `/sentinel/service2` 请求数大于 1 时，再请求 `/sentinel/service1` 会直接报错

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202401261059328.gif" alt="image" style="zoom:100%;float:left" />



### QPS-直接-Warm Up

**QPS-直接 -Warm Up** ：避免低水位服务器突然接收到大量请求宕机采用逐渐放宽限流策略，例如 QPS = 3，预热时长 = 10，冷加载因子默认为 3，就是要让该资源在第 10 秒的时候每秒能够承受每秒 3 次并发请求数量，第一次进行限流的时间点大概在 10/3 次请求时发生

* **阈值类型：**QPS
* **流控模式：**直接 
* **流控效果：**Warm Up

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202401261127321.png" alt="image-20240126112719281" style="zoom:100%;float:left" />



### QPS-直接-排队等待

**QPS-直接-排队等待：**匀速器模式，所有请求堆积在入口处等待，以QPS为准每秒放行响应的请求进行处理，请求间隔为（1/阈值s），可设置超时时间来过滤掉部分等待中的请求，超时时间需要小于请求的间隔才能生效。

* **阈值类型：**QPS
* **流控模式：**直接 
* **流控效果：**排队等待

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202401261135885.png" alt="image-20240126113537846" style="zoom:100%;float:left" />

* 测试结果: 不管每秒有多少请求，只允许有一个请求通过。

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202401261135577.png" alt="image-20240126113516540" style="zoom:80%;float:left" />

* 官网案例

<img src="https://github.com/alibaba/Sentinel/wiki/image/queue.gif" alt="img" style="zoom:81%;float:left" />



## Sentinel 熔断降级

> 官网地址：https://sentinelguard.io/zh-cn/docs/circuit-breaking.html

**服务降级：**当依赖服务不可用时，返回一个备用的默认值或者执行备选方案，确保系统的基本功能仍然可以使用。

例如，支付的时候，可能需要远程调用银联提供的 API；然而，这个被依赖服务的稳定性是不能保证的。如果依赖的服务出现了不稳定的情况，请求的响应时间变长，那么调用服务的方法的响应时间也会变长，线程会产生堆积，最终可能耗尽业务自身的线程池，服务本身也变得不可用。

<img src="https://user-images.githubusercontent.com/9434884/62410811-cd871680-b61d-11e9-9df7-3ee41c618644.png" alt="chain" style="zoom:80%;float:left" />

### 降级策略

在 Sentinel 中，降级策略是一种应对系统负载过大或异常情况的手段，通过限制或拒绝一定比例的请求，以保护系统的稳定性和可靠性。

* **慢调用比例（Slow Request Ratio）：** 指的是在一定时间内，系统处理时间超过预设阈值的请求所占的比例。
* **异常比例（Error Request Ratio）：** 表示系统在一定时间内发生异常的请求所占的比例。异常可以包括各种错误，如超时、网络错误等。
* **异常数（Error Request Number）：**是在一定时间内发生的异常请求的数量。

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202401261424857.png" alt="image-20240126142416821" style="zoom:100%;float:left" />

#### 慢调用比例（Slow Request Ratio）

> 慢调用比例（Slow Request Ratio）: 指的是在一定时间内，系统处理时间超过预设阈值的请求所占的比例。

* 配置类容：我们希望每个请求在 200 ms 处理完成，如果在 200 ms 没有处理完成，我们希望在未来的 2S 中的时间窗口期打开断路器。

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202401261522115.png" alt="image-20240126152225081" style="zoom:100%;float:left" />

* 代码配置

```java
    @GetMapping("/service3")
    public String service3() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return "This is SentinelController.service3() Slow Request Ratio";
    }
```

* 测试配置：可以明显的看到当熔断之后，再次访问直接返回错误信息

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202401261524089.gif" alt="image" style="zoom:100%;float:left" />



#### 异常比例（Error Request Ratio）

> 异常比例（Error Request Ratio）: 表示系统在一定时间内发生异常的请求所占的比例。异常可以包括各种错误，如超时、网络错误等。

* 配置内容：我们希望当请求数量（默认为5）的异常比例大于 80%，我们希望在未来的 3S 中的时间窗口期打开断路器。

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202401261535327.png" alt="image-20240126153506291" style="zoom:100%;float:left" />

* 代码配置

```java
    @GetMapping("/service4")
    public String service4() {
        throw new ArithmeticException("/ by zero");
    }
```

* 测试结果：我们请求的是一个错误页面，但是当请求五次之后，异常比例达到了 80% 直接熔断了服务。

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202401261547407.gif" alt="image" style="zoom:100%;float:left" />



#### 异常数（Error Request Number）

> 异常数（Error Request Number）: 是在一定时间内发生的异常请求的数量。

* 配置内容：是指当资源近1分钟的异常数目超过阈值 8 之后会进行熔断，注意由于统计时间窗口是分钟级别的，若时间窗口小于 60s，则结束熔断状态后仍可能再进入熔断状态

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202401261551256.png" alt="image-20240126155106218" style="zoom:100%;float:left" />

* 测试结果：我们请求的是一个错误页面，但是当请求 9 次之后，异常数达到了 8 次直接熔断了服务。

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202401261557511.gif" alt="image" style="zoom:100%;float:left" />



## Sentinel 热点规则

> 官网地址：https://sentinelguard.io/zh-cn/docs/parameter-flow-control.html

**热点规则：**热点 key 限流是一种在分布式系统中常见的限流策略，它通过对某些热点资源或关键操作进行限制，防止其请求过于频繁，以保护系统的稳定性。其中 Sentinel 主要是以 QPS 作为限流模式

* **QPS 限制（Queries Per Second）：**对于特定的热点 key，限制每秒钟允许的请求次数。

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202401261732982.png" alt="image-20240126173224922" style="zoom:100%;float:left" />

* **代码配置**

```java
    @GetMapping("/service5")
    @SentinelResource(value = "hotKey", blockHandler = "dealHotKey")
    public String service5(@RequestParam(value = "param1",required = false) String param1) {
        return "This is SentinelController.service5() Hot Key";
    }

    public String dealHotKey(String param1, BlockException exception) {
        return "This is SentinelController.service5() dealHotKey: Error";
    }
```

* 我们配置 `/sentinel/service5` 的界面，当参数索引 0 每秒的 QPS 阈值超过了 1 则进行限流

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202401261755719.png" alt="image-20240126175533678" style="zoom:100%;float:left" />

* 测试结果

<img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202401261757401.gif" alt="image" style="zoom:100%;float:left" />



