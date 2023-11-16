package com.project.smartchefservice.service;

import com.project.smartchefservice.entity.Ingredient;
import com.project.smartchefservice.exception.BusinessException;
import com.project.smartchefservice.exception.Status;
import com.project.smartchefservice.exception.TransactionCode;
import com.project.smartchefservice.repository.IngredientRepository;
import com.project.smartchefservice.request.IngredientRequest;
import com.project.smartchefservice.response.DefaultMessageResponse;
import com.project.smartchefservice.response.IngredientResponse;
import com.project.smartchefservice.response.base.BaseBody;
import com.project.smartchefservice.response.body.DefaultMessageBody;
import com.project.smartchefservice.response.body.IngredientBody;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService{

    private final IngredientRepository ingredientRepository;
    @Override
    public IngredientResponse getAllIngredients() {
        List<Ingredient> ingredients = ingredientRepository.findAll();

        IngredientResponse ingredientResponse = new IngredientResponse();
        IngredientBody body = new IngredientBody(ingredients);
        ingredientResponse.setBody(new BaseBody<>(body));
        ingredientResponse.setStatus(new Status(TransactionCode.SUCCESS));
        return ingredientResponse;
    }

    @Override
    public DefaultMessageResponse addIngredient(IngredientRequest ingredientRequest) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientRequest.getName());
        try {
            ingredientRepository.save(ingredient);
        }catch (DataIntegrityViolationException e) {
            throw new BusinessException(TransactionCode.INGREDIENT_ALREADY_EXISTS);
        }


        DefaultMessageResponse defaultMessageResponse = new DefaultMessageResponse();
        DefaultMessageBody body = new DefaultMessageBody("Ingredient added successfully");
        defaultMessageResponse.setBody(new BaseBody<>(body));
        defaultMessageResponse.setStatus(new Status(TransactionCode.SUCCESS));
        return defaultMessageResponse;
    }
}
