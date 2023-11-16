package com.project.smartchefservice.response.body;

import com.project.smartchefservice.entity.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class IngredientBody {
    private List<Ingredient> ingredients;
}
