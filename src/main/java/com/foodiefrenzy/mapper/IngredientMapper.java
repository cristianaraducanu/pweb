package com.foodiefrenzy.mapper;


import com.foodiefrenzy.dto.IngredientDTO;
import com.foodiefrenzy.entity.Ingredient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IngredientMapper extends EntityMapper<IngredientDTO, Ingredient> {

}
