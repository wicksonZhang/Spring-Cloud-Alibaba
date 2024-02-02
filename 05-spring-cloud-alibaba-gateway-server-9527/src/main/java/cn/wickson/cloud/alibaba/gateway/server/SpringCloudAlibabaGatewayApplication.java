package cn.wickson.cloud.alibaba.gateway.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ZhangZiHeng
 * @date 2024-02-01
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "cn.wickson.cloud.alibaba")
public class SpringCloudAlibabaGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAlibabaGatewayApplication.class, args);
    }

}
