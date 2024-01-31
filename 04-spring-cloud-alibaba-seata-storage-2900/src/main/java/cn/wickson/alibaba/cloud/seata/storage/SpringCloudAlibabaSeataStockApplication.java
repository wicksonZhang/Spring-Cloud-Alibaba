package cn.wickson.alibaba.cloud.seata.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "cn.wickson.alibaba.cloud")
public class SpringCloudAlibabaSeataStockApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAlibabaSeataStockApplication.class, args);
    }

}