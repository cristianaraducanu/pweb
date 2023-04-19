package com.foodiefrenzy.repository;

import com.foodiefrenzy.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {
    @Query("SELECT ri FROM RecipeIngredient ri WHERE ri.recipe.id = :recipeId AND ri.ingredient.id = :ingredientId")
    Optional<RecipeIngredient> findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
