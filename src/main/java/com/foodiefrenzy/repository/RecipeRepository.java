package com.foodiefrenzy.repository;

import com.foodiefrenzy.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query("SELECT r FROM Recipe r JOIN RecipeIngredient ri ON r.id = ri.recipe.id WHERE ri.ingredient.id = :ingredientId")
    List<Recipe> findAllByIngredientId(Long ingredientId);
}
