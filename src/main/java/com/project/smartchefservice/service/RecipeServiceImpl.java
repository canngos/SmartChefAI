package com.project.smartchefservice.service;

import com.project.smartchefservice.entity.Ingredient;
import com.project.smartchefservice.exception.BusinessException;
import com.project.smartchefservice.exception.Status;
import com.project.smartchefservice.exception.TransactionCode;
import com.project.smartchefservice.repository.IngredientRepository;
import com.project.smartchefservice.request.RecipeRequest;
import com.project.smartchefservice.response.RecipeResponse;
import com.project.smartchefservice.response.base.BaseBody;
import com.project.smartchefservice.response.body.RecipeResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final ChatService chatService;
    private final IngredientRepository ingredientRepository;
    @Override
    public RecipeResponse generateRecipe(RecipeRequest recipeRequest) {
        StringBuilder ingredientsPromt = new StringBuilder();
        for (Long ingredientId : recipeRequest.getIngredients()) {
            Optional<Ingredient> ingredient = ingredientRepository.findById(ingredientId);
            if (ingredient.isEmpty()) {
                throw new BusinessException(TransactionCode.INGREDIENT_NOT_FOUND);
            }
            ingredientsPromt.append(ingredient.get().getName()).append(", ");
        }

        String recipes = chatService.getRecipies(ingredientsPromt.toString());

        RecipeResponse recipeResponse = new RecipeResponse();
        RecipeResponseBody body = new RecipeResponseBody(recipes);
        recipeResponse.setBody(new BaseBody<>(body));
        recipeResponse.setStatus(new Status(TransactionCode.SUCCESS));
        return recipeResponse;
    }

}
