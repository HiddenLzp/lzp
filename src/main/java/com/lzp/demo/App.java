package com.lzp.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.Entity;

/**
 * @Author LiZePing
 * @date2019/5/27 16:30
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.lzp.demo.dao.jpa"})
@EntityScan(basePackages = "com.lzp.demo.model")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
