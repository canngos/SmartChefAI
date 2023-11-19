package com.project.smartchefservice.service;

import com.project.smartchefservice.request.RecipeRequest;
import com.project.smartchefservice.response.DefaultMessageResponse;
import com.project.smartchefservice.response.RecipeHistoryResponse;
import com.project.smartchefservice.response.RecipeResponse;

import java.util.concurrent.ExecutionException;

public interface RecipeService {
    RecipeResponse generateRecipe(RecipeRequest recipeRequest) throws ExecutionException, InterruptedException;
    RecipeHistoryResponse getRecipeHistory();
    DefaultMessageResponse deleteRecipeHistory(Long id);
}
