package com.project.smartchefservice.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientRequest {
    @NotBlank(message = "Ingredient name cannot be empty")
    private String name;
}
