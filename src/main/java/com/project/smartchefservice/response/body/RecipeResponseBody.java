package com.project.smartchefservice.response.body;

import com.project.smartchefservice.dto.Recipe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RecipeResponseBody {
    private List<Recipe> recipes;
}
