package ru.project.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.PriorityQueue;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import ru.project.exception.ValidationException;
import ru.project.model.NumberResponse;

@Service
public class NumberServiceImpl implements NumberService {

    private static final Logger log = LoggerFactory.getLogger(NumberService.class);

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
