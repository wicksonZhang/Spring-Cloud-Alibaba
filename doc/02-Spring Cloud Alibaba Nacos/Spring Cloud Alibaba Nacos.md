# Spring Cloud Alibaba Nacos

> 

## 基础概念

### Nacos 解决了什么问题

​		Nacos 主要解决了微服务中服务注册与发现和服务配置等，在 `Spring Cloud` 中如果需要实现服务注册与发现和配置需要使用到 `Eureka` 、`Spring Cloud Config` 和 `Spring Cloud Bus` 。但现在只需要使用一个 `Nacos` 就可以解决问题。

* **服务注册与发现**

  <img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202401231110918.png" alt="service-discovery-0257df233735d6e39adc8e8a0fd27f86" style="zoom:33%;float:left" />

* **服务配置**

  <img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202401231106290.png" alt="service-config" style="zoom: 22.5%;float:left" />



### Nacos 是什么

> 官网地址：https://nacos.io/
>
> Github地址：https://github.com/alibaba/nacos

Nacos：`Dynamic Naming and Configuration Service` 的首字母简称，一个更易于构建云原生应用的动态服务发现、配置管理和服务管理平台。它由阿里巴巴开源团队维护，旨在为现代微服务架构提供一套集成的解决方案。



### 注册中心对比

以下是 `Nacos` 相对于 `Eureka` 的一些优点：

1. **动态配置管理：** `Nacos` 不仅提供了服务注册与发现的功能，还集成了动态配置管理。
2. **健康检查更灵活：** `Nacos` 提供了更灵活的健康检查机制，支持不同的健康检查方式和策略。
3. **更强大的集群管理：** `Nacos` 在集群管理方面更为强大，支持多数据中心、多命名空间的配置管理和服务发现。

| 特性 / 注册中心    | Nacos                         | Eureka                                | Zookeeper                      | Consul                         |
| ------------------ | ----------------------------- | ------------------------------------- | ------------------------------ | ------------------------------ |
| **服务发现**       | ✔ 支持                        | ✔ 支持                                | ✔ 支持                         | ✔ 支持                         |
| **配置管理**       | ✔ 支持                        | ✖ 不支持（需结合Spring Cloud Config） | ✖ 不支持（可结合其他配置中心） | ✖ 不支持（可结合其他配置中心） |
| **多数据中心支持** | ✔ 支持                        | ✔ 支持                                | ✖ 不支持                       | ✔ 支持                         |
| **动态 DNS 服务**  | ✔ 支持                        | ✖ 不支持                              | ✖ 不支持                       | ✖ 不支持                       |
| **健康检查**       | ✔ 支持                        | ✖ 不支持（需结合Spring Cloud Health） | ✔ 支持                         | ✔ 支持                         |
| **事件监听机制**   | ✔ 支持                        | ✔ 支持                                | ✔ 支持                         | ✔ 支持                         |
| **支持协议**       | HTTP、DNS、gRPC等             | HTTP                                  | 客户端/服务器模型，可扩展协议  | HTTP，支持DNS                  |
| **一致性协议**     | CP + AP                       | AP（强调可用性和分区容忍性）          | CP 协议（强一致性）            | CP + AP协议                    |
| **生态系统整合**   | Spring Cloud Alibaba 生态系统 | Spring Cloud 生态系统                 | 社区广泛使用，适用于多种场景   | Spring Cloud 生态系统          |
| **开发维护**       | 阿里巴巴维护，活跃的社区      | Netflix 维护，社区逐渐减少            | Apache 维护，社区广泛使用      | HashiCorp 维护，社区活跃       |



## Nacos 安装启动

> 下载地址：https://github.com/alibaba/nacos
>
> 下载版本：nacos-server-2.0.1.zip

* 下载完成之后，解压到指定目录：

  <img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202401231402963.png" alt="image-20240123140212936" style="zoom:100%;float:left" />



* 启动命令如下，如果看到了对应图标且不报错说明启动成功

  * 默认启动是集群模式：startup.cmd
  * 如果需要指定为单机模式：startup.cmd -m standalone

  ```tex
  D:\software\nacos\bin>startup.cmd -m standalone
  "nacos is starting with standalone"
  ```



* 访问地址：http://localhost:8848/nacos

  * 用户名和密码都是 nacos

  <img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202401231409649.png" alt="image-20240123140904614" style="zoom:100%;float:left" />

  

## Nacos 单机操作

* **实现需求**

  ```tex
  1. 当访问 消费者服务接口 时，消费者服务 通过对应的路由将请求分发到具体的 服务提供者 中。
  2. 服务提供者 通过读取配置中心的内容，将消息返回给消费者服务。
  ```

* **实现结果**

  ![动画](https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202401231550837.gif)

* **代码结构**

  <img src="https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202401231552005.png" alt="image-20240123155245973" style="zoom:100%;float:left" />

* 实现思路

  ```tex
  1. 创建 提供者1 服务，并将其注册到Nacos中。
  2. 创建 提供者2 服务，并将其注册到Nacos中。
  3. 创建 消费者 服务，并将其注册到Nacos中，通过 Nacos 的负载均衡将消费者的请求转发到服务提供者。
  ```

  

### 服务提供者1

**实现步骤**

```tex
Step-1: 创建 服务提供者1 02-spring-cloud-alibaba-nacos-producer1-2100
Step-2: 导入 pom.xml 依赖
Step-3: 创建 bootstrap.yml、application.yml
Step-4: 创建启动类 SpringCloudAlibabaProducer1Application
Step-5: 创建控制类 Producer1Controller
```



**Step-2: 导入 pom.xml 依赖**

```xml
<dependencies>
    <!-- 引入公共依赖 common -->
    <dependency>
        <groupId>cn.wickson.cloud.alibaba</groupId>
        <artifactId>01-spring-cloud-alibaba-common</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>

    <!-- Spring Cloud alibaba nacos -->
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
    </dependency>

    <!-- Spring Cloud alibaba config -->
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
    </dependency>
</dependencies>
```



**Step-3: 创建 bootstrap.yml、application.yml**

* `bootstrap.yml`： 主要用于应用程序的引导阶段，会优先被加载。

```yml
# ------------------------- 应用端口 -------------------------
server:
  port: 2100

spring:
  application:
    name: spring-cloud-alibaba-nacos-producer
  cloud:
# -------------------------------- nacos地址 ---------------------------------
    nacos:
      discovery:
        server-addr: localhost:8848 # nacos 服务注册中心地址
      config:
        server-addr: localhost:8848 # nacos 服务配置中心地址
        file-extension: yaml
# -------------------------------- nacos地址 ---------------------------------

#--------------------------------- 客户端配置暴露监控端点 start ---------------------------------
management:
  endpoints:
    web:
      exposure:
        include: "*"
#--------------------------------- 客户端配置暴露监控端点 end   ---------------------------------
```

* `application.yml`：主要用于应用程序的主配置文件，如数据库连接、服务端口。

```yml
spring:
  profiles:
    active: dev # 开发环境
```



**Step-4: 创建启动类 SpringCloudAlibabaProducer1Application**

```java
/**
 * Spring Cloud Alibaba 生产者启动类
 *
 * @author ZhangZiHeng
 * @date 2024-01-23
 */
@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudAlibabaProducer1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAlibabaProducer1Application.class, args);
    }

}
```



**Step-5: 创建控制类 Producer1Controller**

```java
@Slf4j
@Validated
@RestController
@RefreshScope // 支持 Nacos 动态刷新配置
@RequestMapping("/producer")
public class Producer1Controller {

    @Value("${server.port}")
    private Integer serverPort;

    @Value("${config.info}")
    private String configInfo;

    @GetMapping(value = "/nacos")
    public String getProducerConfig() {
        return "The producer1 server.port: " + serverPort + " registered to nacos.The producer1 config：" + configInfo;
    }

}
```



### 服务提供者2

**实现步骤**

```tex
Step-1: 创建 服务提供者1 02-spring-cloud-alibaba-nacos-producer2-2200
Step-2: 导入 pom.xml 依赖
Step-3: 创建 bootstrap.yml、application.yml
Step-4: 创建启动类 SpringCloudAlibabaProducer2Application
Step-5: 创建控制类 Producer2Controller
```

**操作与服务提供者保持一致**



### 服务消费者

**实现步骤**

```tex
Step-1: 创建 服务消费者 02-spring-cloud-alibaba-nacos-consumer-2300
Step-2: 导入 pom.xml 依赖
Step-3: 创建 application.yml
Step-4: 创建启动类 SpringCloudAlibabaConsumerApplication
Step-5: 创建控制类 ConsumerController
Step-6: 创建 RestTemplate 配置类
```



**Step-2: 导入 pom.xml 依赖**

```xml
<dependencies>
    <!-- 公共依赖 common -->
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

</dependencies>
```



**Step-3: 创建 `application.yml`**

```yml
# ------------------------- 应用端口 -------------------------
server:
  port: 2300

spring:
  application:
    name: spring-cloud-alibaba-nacos-consumer
  cloud:
# -------------------------------- nacos地址 ---------------------------------
    nacos:
      discovery:
        server-addr: localhost:8848 # nacos 服务注册中心地址
# -------------------------------- nacos地址 ---------------------------------

# ------------------------------- 服务提供者路由 -------------------------------
service-url:
  nacos-user-service: http://spring-cloud-alibaba-nacos-producer
# ------------------------------- 服务提供者路由 -------------------------------
```



**Step-4: 创建启动类 SpringCloudAlibabaConsumerApplication**

```java
/**
 * Spring Cloud Alibaba Consumer 启动类
 *
 * @author ZhangZiHeng
 * @date 2024-01-23
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "cn.wickson.cloud.alibaba")
public class SpringCloudAlibabaConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAlibabaConsumerApplication.class, args);
    }

}
```



**Step-5: 创建控制类 ConsumerController**

```java
/**
 * 消费者控制类
 *
 * @author ZhangZiHeng
 * @date 2024-01-23
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverUrl;

    @GetMapping("/getProducer")
    public String getProducer() {
        return restTemplate.getForObject(serverUrl + "/producer/nacos", String.class);
    }

}
```



**Step-6: 创建 RestTemplate 配置类**

```java
/**
 * RestTemplateConfig 用作负载均衡配置
 *
 * @author ZhangZiHeng
 * @date 2024-01-23
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
```



### 服务注册中心

当 服务提供者1、服务提供者2、服务消费者 启动时，查看 服务管理 --> 服务列表

![image-20240123163334025](https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202401231633076.png)



### 服务配置中心

由于我们需要通过在服务提供者中读取 `config.info` 的配置文件信息

* **注意：Data ID 配置规则如下：**

```tex
Data ID = ${spring.application.name}-${spring.profiles.active}.${spring.cloud.nacos.config.file-extension}
```

![image-20240123163538760](https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202401231635810.png)

* 我们需要在 **配置管理 --> 配置列表 --> +** 中创建并配置如下内容

![image-20240123163647874](https://cdn.jsdelivr.net/gh/wicksonZhang/static-source-cdn/images/202401231636929.png)
