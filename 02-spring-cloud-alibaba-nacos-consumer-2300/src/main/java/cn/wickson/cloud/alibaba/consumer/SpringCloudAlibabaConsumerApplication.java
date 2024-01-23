package cn.wickson.cloud.alibaba.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

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
