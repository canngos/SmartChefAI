package com.project.smartchefservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum TransactionCode {
    SUCCESS(100, "Success", HttpStatus.OK),
    INGREDIENT_NOT_FOUND(101, "Ingredient not found", HttpStatus.NOT_FOUND),
    INGREDIENT_ALREADY_EXISTS(102, "Ingredient already exists", HttpStatus.BAD_REQUEST);


    private final int id;
    private final String code;
    private final HttpStatus httpStatus;

    TransactionCode(int id, String code, HttpStatus httpStatus) {
        this.id = id;
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
