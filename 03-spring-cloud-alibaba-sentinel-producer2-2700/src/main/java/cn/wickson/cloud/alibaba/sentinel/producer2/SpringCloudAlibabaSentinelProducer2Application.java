package cn.wickson.cloud.alibaba.sentinel.producer2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Spring Cloud Alibaba Sentinel 生产者启动类
 *
 * @author ZhangZiHeng
 * @date 2024-01-29
 */
@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudAlibabaSentinelProducer2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAlibabaSentinelProducer2Application.class, args);
    }

}
