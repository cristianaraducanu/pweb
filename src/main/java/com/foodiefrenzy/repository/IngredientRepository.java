package com.foodiefrenzy.repository;

import com.foodiefrenzy.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    @Query("SELECT i FROM Ingredient i JOIN RecipeIngredient ri ON i.id = ri.ingredient.id WHERE ri.recipe.id = :recipeId")
    List<Ingredient> findAllByRecipeId(Long recipeId);
}
