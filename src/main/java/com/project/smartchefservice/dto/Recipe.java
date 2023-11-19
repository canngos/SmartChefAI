package com.project.smartchefservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Recipe {
    private String recipe_name;
    private List<String> steps;
}
