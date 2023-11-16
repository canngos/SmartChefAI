package com.project.smartchefservice.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecipeRequest {
    @NotBlank(message = "ingredients is mandatory")
    @Min(value = 3, message = "ingredients must have at least 1 element")
    @Max(value = 10, message = "ingredients must have at most 10 elements")
    private List<Long> ingredients;
}
