package com.project.smartchefservice.service;

import com.project.smartchefservice.request.RecipeRequest;
import com.project.smartchefservice.response.RecipeResponse;

public interface RecipeService {
    RecipeResponse generateRecipe(RecipeRequest recipeRequest);
}
