package ru.project.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.project.model.NumberResponse;
import ru.project.service.NumberService;

import java.io.IOException;

@RestController
@Tag(name = "NumberController", description = "Контроллер для работы с Excel-файлами")
@Validated
public class NumberController {

    @Autowired
    private NumberService numberService;

    @GetMapping("/n-max-number")
    @Operation(summary = "Вернуть N-ное максимальное число",
               description = "Принимает путь к локальному файлу(xlsx) и число N, возвращает N-ное максимальное число")
    public NumberResponse findNMaxNumber(@RequestParam String filePath,
                                         @RequestParam @Positive int n) throws IOException {
        return numberService.findNMaxNumber(filePath, n);
    }
}
