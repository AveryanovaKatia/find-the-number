package ru.project;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class FindTheNumberApplication {

    public static void main(String[] args) {
        SpringApplication.run(FindTheNumberApplication.class, args);

        log.info("Приложение FindTheNumberApplication запущено");
    }
}
