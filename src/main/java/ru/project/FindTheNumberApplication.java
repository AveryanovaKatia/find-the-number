package ru.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class FindTheNumberApplication {

    private static final Logger log = LoggerFactory.getLogger(FindTheNumberApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(FindTheNumberApplication.class, args);

        log.info("Приложение FindTheNumberApplication запущено");
    }
}
