package com.ssn.middleware.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author linchengdong
 * @Date 2024-05-17 16:59
 * @PackageName:com.ssn.middleware.admin
 * @ClassName: MinitorApplication
 * @Description: TODO
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan(basePackages = "com.ssn.middleware.admin.mapper")
public class MonitorManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorManagerApplication.class,args);
    }
}
