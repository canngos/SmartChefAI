package com.project.smartchefservice.controller;

import com.project.smartchefservice.request.RecipeRequest;
import com.project.smartchefservice.response.DefaultMessageResponse;
import com.project.smartchefservice.response.RecipeHistoryResponse;
import com.project.smartchefservice.response.RecipeResponse;
import com.project.smartchefservice.service.RecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/recipe/v1.0")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping(value = "/generate")
    public ResponseEntity<RecipeResponse> generateRecipe(@Valid @RequestBody RecipeRequest recipeRequest) throws ExecutionException, InterruptedException {
        return new ResponseEntity<>(recipeService.generateRecipe(recipeRequest), HttpStatus.OK);
    }

    @GetMapping(value = "/history")
    public ResponseEntity<RecipeHistoryResponse> getRecipeHistory() {
        return new ResponseEntity<>(recipeService.getRecipeHistory(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/history/{id}")
    public ResponseEntity<DefaultMessageResponse> deleteRecipeHistory(@PathVariable Long id) {
        return new ResponseEntity<>(recipeService.deleteRecipeHistory(id), HttpStatus.OK);
    }




}
