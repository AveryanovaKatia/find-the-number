package ru.project.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.PriorityQueue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.project.exception.ValidationException;
import ru.project.model.NumberResponse;

@Slf4j
@Service
public class NumberServiceImpl implements NumberService {

    @Override
    public NumberResponse findNMaxNumber(String filePath, int n) throws IOException {

        log.info("Считываем данные из файла");
        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(file)) {
            Sheet sheet = workbook.getSheetAt(0);

            final PriorityQueue<Integer> minHeap = new PriorityQueue<>();

            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.NUMERIC) {
                        int number = (int) cell.getNumericCellValue();

                        if (!minHeap.contains(number)) {
                            log.info("Добавляем в очередь новое число");
                            minHeap.add(number);
                        }
                        if (minHeap.size() > n) {
                            log.info("В очереди количество чисел превышает N, удаляем самое маленькое");
                            minHeap.poll();
                        }
                    }
                }
            }

            workbook.close();
            file.close();

            if (minHeap.size() < n) {
                throw new ValidationException("В файле нет необходимого количества чисел");
            } else {
                log.info("Возвращаем N-ое максимальное число");
                return new NumberResponse(minHeap.peek());
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("По заданному пути файл не найден: " + filePath);
        } catch (IOException e) {
            throw new IOException("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}
