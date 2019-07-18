package com.gmail.shilovich.stas.bot.springboot.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication(scanBasePackages = "com.gmail.shilovich.stas.bot")
@EntityScan("com.gmail.shilovich.stas.bot.repository.model")
public class SpringbootApplication {

    public static void main(String[] args) {

        ApiContextInitializer.init();
        SpringApplication.run(SpringbootApplication.class, args);
    }
}
