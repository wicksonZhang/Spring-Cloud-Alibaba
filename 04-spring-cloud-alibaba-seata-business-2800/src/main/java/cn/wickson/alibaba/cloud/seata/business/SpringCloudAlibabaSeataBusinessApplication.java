package cn.wickson.alibaba.cloud.seata.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "cn.wickson.alibaba.cloud")
public class SpringCloudAlibabaSeataBusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAlibabaSeataBusinessApplication.class, args);
    }

}