package com.foodiefrenzy.mapper;


import com.foodiefrenzy.dto.RecipeDTO;
import com.foodiefrenzy.entity.Recipe;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = RecipeIngredientMapper.class)
public interface RecipeMapper extends EntityMapper<RecipeDTO, Recipe> {
    @Mapping(source = "category.id", target = "categoryId")
    RecipeDTO toDto(Recipe entity, @Context RecipeIngredientMapper recipeIngredientMapper);

    @Mapping(source = "categoryId", target = "category.id")
    Recipe toEntity(RecipeDTO dto, @Context RecipeIngredientMapper recipeIngredientMapper);

}
