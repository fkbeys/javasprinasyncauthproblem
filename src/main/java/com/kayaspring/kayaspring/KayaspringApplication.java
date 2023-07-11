package com.kayaspring.kayaspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class KayaspringApplication {


    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(KayaspringApplication.class);
        app.run(args);
    }

}
