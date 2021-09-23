package com.itheima.www;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;

@SpringBootApplication
public class ItheimaMyJpaDay2Application implements CommandLineRunner {
    @Autowired
    DataSource dataSource;
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ItheimaMyJpaDay2Application.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        //打印默认的数据源连接池
        System.out.println(dataSource.getClass());
    }
}
