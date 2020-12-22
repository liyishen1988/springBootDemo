package com.lys.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;

/**
 * 程序启动入口
 */
@SpringBootApplication
@EnableTransactionManagement//开启事务管理
public class SpringBootDemoApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }
	

}
