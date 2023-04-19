package com.foodiefrenzy.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RecipeDTO {
    private Long id;

    private String name;

    private String description;

    private Double time;

    private Long categoryId;

    private List<RecipeIngredientDTO> ingredients = new ArrayList<>();
}
