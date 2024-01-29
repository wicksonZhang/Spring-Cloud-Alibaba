package cn.wickson.cloud.alibaba.sentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Spring Cloud Alibaba Sentinel 启动类
 *
 * @author ZhangZiHeng
 * @date 2024-01-25
 */
@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudAlibabaSentinelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAlibabaSentinelApplication.class, args);
    }

}
