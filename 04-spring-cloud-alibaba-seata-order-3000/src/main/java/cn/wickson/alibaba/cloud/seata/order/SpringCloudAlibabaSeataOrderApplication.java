package cn.wickson.alibaba.cloud.seata.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "cn.wickson.alibaba.cloud")
public class SpringCloudAlibabaSeataOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAlibabaSeataOrderApplication.class, args);
    }

}