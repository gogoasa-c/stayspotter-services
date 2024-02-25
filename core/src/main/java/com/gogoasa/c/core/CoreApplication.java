package com.gogoasa.c.core;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CoreApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {}
}
