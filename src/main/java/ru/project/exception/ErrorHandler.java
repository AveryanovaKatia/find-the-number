package ru.project.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.project.controller.NumberController;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Slf4j
@RestControllerAdvice(assignableTypes = {NumberController.class})
public class ErrorHandler {

    @ExceptionHandler({ValidationException.class, FileNotFoundException.class, Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValid(final Exception e) {
        log.error("Исключение, {}: {}", e, e.getMessage());
        return Map.of("status", "BAD_REQUEST",
                "reason", "Неправильно составлен запрос",
                "message", e.getMessage(),
                "timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
