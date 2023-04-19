package com.foodiefrenzy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeIngredientDTO {
    private Long ingredientId;

    private Long recipeId;

    private Double amount;
}
