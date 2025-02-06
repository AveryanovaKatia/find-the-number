package ru.project.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NumberResponse {

    private int nMaxNumber;

    public NumberResponse(Integer number) {
        this.nMaxNumber = number;
    }
}
