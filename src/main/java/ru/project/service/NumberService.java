package ru.project.service;

import ru.project.model.NumberResponse;

import java.io.IOException;

public interface NumberService {

   NumberResponse findNMaxNumber(String filePath, int n) throws IOException;
}
