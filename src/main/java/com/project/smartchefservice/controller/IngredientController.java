package com.project.smartchefservice.controller;

import com.project.smartchefservice.request.IngredientRequest;
import com.project.smartchefservice.response.DefaultMessageResponse;
import com.project.smartchefservice.response.IngredientResponse;
import com.project.smartchefservice.service.IngredientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient/v1.0")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping
    public ResponseEntity<IngredientResponse> getAllIngredients() {
        return new ResponseEntity<>(ingredientService.getAllIngredients(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DefaultMessageResponse> addIngredient(@Valid @RequestBody IngredientRequest ingredientRequest) {
        return new ResponseEntity<>(ingredientService.addIngredient(ingredientRequest), HttpStatus.OK);
    }
}
