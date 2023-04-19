package com.foodiefrenzy.service;

import com.foodiefrenzy.dto.RecipeDTO;
import com.foodiefrenzy.entity.Ingredient;
import com.foodiefrenzy.entity.Recipe;
import com.foodiefrenzy.entity.RecipeIngredient;
import com.foodiefrenzy.mapper.RecipeMapper;
import com.foodiefrenzy.repository.IngredientRepository;
import com.foodiefrenzy.repository.RecipeIngredientRepository;
import com.foodiefrenzy.repository.RecipeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;

    private final IngredientRepository ingredientRepository;

    private final RecipeIngredientRepository recipeIngredientRepository;

    private final RecipeMapper recipeMapper;

    public RecipeDTO createDTO(RecipeDTO recipeDTO) {
        Recipe recipe = recipeMapper.toEntity(recipeDTO);

        List<RecipeIngredient> ingredients = new ArrayList<>();
        for (RecipeIngredient recipeIngredient : recipe.getIngredients()) {
            Long ingredientId = recipeIngredient.getIngredient().getId();
            Ingredient ingredient = ingredientRepository.findById(ingredientId)
                    .orElseThrow(() -> new EntityNotFoundException("Ingredient with id " + ingredientId + " not found."));
            recipeIngredient.setRecipe(recipe);
            recipeIngredient.setIngredient(ingredient);
            ingredients.add(recipeIngredient);
        }

        Recipe saved = recipeRepository.save(recipe);
        RecipeDTO recipeSaved = recipeMapper.toDto(saved);
        recipeIngredientRepository.saveAll(ingredients);
        return recipeSaved;
    }

    public RecipeDTO update(RecipeDTO updated) {
        Optional<Recipe> recipeExisting = recipeRepository.findById(updated.getId());
        if (recipeExisting.isEmpty()) {
            throw new EntityNotFoundException("Recipe with id " + updated.getId() + " not found.");
        }

        Recipe recipe = recipeMapper.toEntity(updated);

        List<RecipeIngredient> ingredients = new ArrayList<>();
        for (RecipeIngredient recipeIngredient : recipe.getIngredients()) {
            Long ingredientId = recipeIngredient.getIngredient().getId();
            Ingredient ingredient = ingredientRepository.findById(ingredientId)
                    .orElseThrow(() -> new EntityNotFoundException("Ingredient with id " + ingredientId + " not found"));

            Optional<RecipeIngredient> recipeIngredientExistent = recipeIngredientRepository
                    .findByRecipeIdAndIngredientId(recipe.getId(), ingredient.getId());
            if (recipeIngredientExistent.isEmpty()) {
                recipeIngredient.setIngredient(ingredient);
                ingredients.add(recipeIngredient);
            }
        }
        Recipe saved = recipeRepository.save(recipe);
        RecipeDTO recipeSaved = recipeMapper.toDto(saved);
        ingredients.forEach(ingredient -> ingredient.setRecipe(saved));
        recipeIngredientRepository.saveAll(ingredients);

        return recipeSaved;
    }

    public RecipeDTO getById(Long id) {
        return recipeMapper.toDto(recipeRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Recipe with id " + id + " not found.")));
    }

    public List<RecipeDTO> getAll() {
        return recipeRepository.findAll()
                .stream()
                .map(recipeMapper::toDto)
                .toList();
    }

    public List<RecipeDTO> getAllByIngredientId(Long ingredientId) {
        return recipeRepository.findAllByIngredientId(ingredientId)
                .stream()
                .map(recipeMapper::toDto)
                .toList();
    }

    public void delete(Long id) {
        recipeRepository.deleteById(id);
    }
}
