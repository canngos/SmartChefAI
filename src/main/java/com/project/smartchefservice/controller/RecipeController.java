package com.project.smartchefservice.controller;

import com.project.smartchefservice.request.RecipeRequest;
import com.project.smartchefservice.response.RecipeResponse;
import com.project.smartchefservice.service.RecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe/v1.0")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping(value = "/generate")
    public ResponseEntity<RecipeResponse> generateRecipe(@Valid @RequestBody RecipeRequest recipeRequest) {
        return new ResponseEntity<>(recipeService.generateRecipe(recipeRequest), HttpStatus.OK);
    }

}
