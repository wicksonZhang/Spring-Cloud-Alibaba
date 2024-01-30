package cn.wickson.cloud.alibaba.sentinel.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Spring Cloud Alibaba Sentinel 消费者启动类
 *
 * @author ZhangZiHeng
 * @date 2024-01-29
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class SpringCloudAlibabaSentinelConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAlibabaSentinelConsumerApplication.class, args);
    }

}
