package cn.wickson.cloud.alibaba.producer1;

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
public class SpringCloudAlibabaProducer1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAlibabaProducer1Application.class, args);
    }

}
