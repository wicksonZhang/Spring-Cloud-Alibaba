package cn.wickson.cloud.alibaba.producer2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Spring Cloud Alibaba 生产者启动类
 *
 * @author ZhangZiHeng
 * @date 2024-01-23
 */
@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudAlibabaProducer2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAlibabaProducer2Application.class, args);
    }

}
