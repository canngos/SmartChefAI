package com.project.smartchefservice.service;

import com.project.smartchefservice.dto.Recipe;
import com.project.smartchefservice.dto.Recipes;
import com.project.smartchefservice.entity.Ingredient;
import com.project.smartchefservice.entity.RecipeHistory;
import com.project.smartchefservice.exception.BusinessException;
import com.project.smartchefservice.exception.Status;
import com.project.smartchefservice.exception.TransactionCode;
import com.project.smartchefservice.repository.IngredientRepository;
import com.project.smartchefservice.repository.RecipeHistoryRepository;
import com.project.smartchefservice.request.RecipeRequest;
import com.project.smartchefservice.response.DefaultMessageResponse;
import com.project.smartchefservice.response.RecipeHistoryResponse;
import com.project.smartchefservice.response.RecipeResponse;
import com.project.smartchefservice.response.base.BaseBody;
import com.project.smartchefservice.response.body.DefaultMessageBody;
import com.project.smartchefservice.response.body.RecipeHistoryResponseBody;
import com.project.smartchefservice.response.body.RecipeResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final ChatService chatService;
    private final IngredientRepository ingredientRepository;
    private final RecipeHistoryRepository recipeHistoryRepository;
    @Override
    public RecipeResponse generateRecipe(RecipeRequest recipeRequest) throws ExecutionException, InterruptedException {
        StringBuilder ingredientsPromt = new StringBuilder();
        ingredientsPromt.append("\"");
        for (Long ingredientId : recipeRequest.getIngredients()) {
            Optional<Ingredient> ingredient = ingredientRepository.findById(ingredientId);
            if (ingredient.isEmpty()) {
                throw new BusinessException(TransactionCode.INGREDIENT_NOT_FOUND);
            }
            ingredientsPromt.append(ingredient.get().getName()).append(", ");
        }
        // delete last comma and space
        ingredientsPromt.delete(ingredientsPromt.length() - 2, ingredientsPromt.length());
        ingredientsPromt.append("\"");

        CompletableFuture<Recipes> recipesFuture = chatService.getRecipes(ingredientsPromt.toString());
        Recipes recipes = recipesFuture.get();
        // save recipes to db
        for (Recipe recipe : recipes.getRecipes()) {
            String name = recipe.getRecipe_name();
            Optional<RecipeHistory> recipeHistoryOptional = recipeHistoryRepository.findByRecipeName(name);

            if (recipeHistoryOptional.isEmpty()) {
                RecipeHistory recipeHistory = new RecipeHistory();
                recipeHistory.setRecipeName(name);
                recipeHistory.setSteps(recipe.getSteps());
                recipeHistoryRepository.save(recipeHistory);
            } else {
                RecipeHistory recipeHistory = recipeHistoryOptional.get();
                recipe.setSteps(recipeHistory.getSteps());
            }
        }
        RecipeResponse recipeResponse = new RecipeResponse();
        RecipeResponseBody body = new RecipeResponseBody(recipes.getRecipes());
        recipeResponse.setBody(new BaseBody<>(body));
        recipeResponse.setStatus(new Status(TransactionCode.SUCCESS));
        return recipeResponse;
    }

    @Override
    public RecipeHistoryResponse getRecipeHistory() {
        List<RecipeHistory> recipeHistoryList = recipeHistoryRepository.findAllByOrderByCreatedAtDesc();
        RecipeHistoryResponse recipeHistoryResponse = new RecipeHistoryResponse();
        RecipeHistoryResponseBody body = new RecipeHistoryResponseBody(recipeHistoryList);
        recipeHistoryResponse.setBody(new BaseBody<>(body));
        recipeHistoryResponse.setStatus(new Status(TransactionCode.SUCCESS));
        return recipeHistoryResponse;
    }

    @Override
    public DefaultMessageResponse deleteRecipeHistory(Long id) {
        recipeHistoryRepository.deleteById(id);
        DefaultMessageResponse defaultMessageResponse = new DefaultMessageResponse();
        DefaultMessageBody body = new DefaultMessageBody("Recipe deleted successfully");
        defaultMessageResponse.setBody(new BaseBody<>(body));
        defaultMessageResponse.setStatus(new Status(TransactionCode.SUCCESS));
        return defaultMessageResponse;
    }

}
