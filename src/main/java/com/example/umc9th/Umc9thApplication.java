package com.example.umc9th;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // 👈 이 어노테이션을 꼭 추가해주세요!
public class Umc9thApplication {
    public static void main(String[] args) {
        SpringApplication.run(Umc9thApplication.class, args);
    }
}