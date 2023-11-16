package com.project.smartchefservice.service;

import com.project.smartchefservice.request.IngredientRequest;
import com.project.smartchefservice.response.DefaultMessageResponse;
import com.project.smartchefservice.response.IngredientResponse;

public interface IngredientService {
    IngredientResponse getAllIngredients();
    DefaultMessageResponse addIngredient(IngredientRequest ingredientRequest);
}
