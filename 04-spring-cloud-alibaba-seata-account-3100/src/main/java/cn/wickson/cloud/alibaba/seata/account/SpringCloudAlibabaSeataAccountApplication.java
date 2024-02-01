package cn.wickson.cloud.alibaba.seata.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "cn.wickson.cloud.alibaba")
public class SpringCloudAlibabaSeataAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAlibabaSeataAccountApplication.class, args);
    }

}