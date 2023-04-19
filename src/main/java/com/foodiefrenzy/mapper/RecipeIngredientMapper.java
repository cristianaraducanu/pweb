package com.foodiefrenzy.mapper;


import com.foodiefrenzy.dto.RecipeIngredientDTO;
import com.foodiefrenzy.entity.RecipeIngredient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecipeIngredientMapper extends EntityMapper<RecipeIngredientDTO, RecipeIngredient> {

    @Mapping(source = "recipeId", target = "recipe.id")
    @Mapping(source = "ingredientId", target = "ingredient.id")
    RecipeIngredient toEntity(RecipeIngredientDTO dto);

    @Mapping(source = "recipe.id", target = "recipeId")
    @Mapping(source = "ingredient.id", target = "ingredientId")
    RecipeIngredientDTO toDto(RecipeIngredient entity);

}
