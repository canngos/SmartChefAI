package com.project.smartchefservice.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecipeRequest {
    @Size(min = 1, max = 10, message = "ingredients must be between 1 and 10")
    private List<Long> ingredients;
}
