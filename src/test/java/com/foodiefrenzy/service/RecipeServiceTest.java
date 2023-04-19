package com.foodiefrenzy.service;

import com.foodiefrenzy.dto.RecipeDTO;
import com.foodiefrenzy.dto.RecipeIngredientDTO;
import com.foodiefrenzy.entity.Ingredient;
import com.foodiefrenzy.entity.Recipe;
import com.foodiefrenzy.entity.RecipeIngredient;
import com.foodiefrenzy.mapper.RecipeMapper;
import com.foodiefrenzy.repository.IngredientRepository;
import com.foodiefrenzy.repository.RecipeIngredientRepository;
import com.foodiefrenzy.repository.RecipeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {
    @Mock
    private RecipeRepository recipeRepository;
    @Mock
    private IngredientRepository ingredientRepository;
    @Mock
    private RecipeIngredientRepository recipeIngredientRepository;
    @Mock
    private RecipeMapper recipeMapper;

    private RecipeService recipeService;

    @BeforeEach
    void setUp() {
        recipeService = new RecipeService(recipeRepository, ingredientRepository, recipeIngredientRepository, recipeMapper);
    }

    @Test
    void testCreateDTO() {
        RecipeDTO recipeDTO = new RecipeDTO();
        Recipe recipe = new Recipe();
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);

        List<RecipeIngredient> ingredients = new ArrayList<>();
        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setIngredient(ingredient);
        ingredients.add(recipeIngredient);

        List<RecipeIngredientDTO> ingredientsDTO = new ArrayList<>();
        RecipeIngredientDTO recipeIngredientDTO = new RecipeIngredientDTO();
        ingredientsDTO.add(recipeIngredientDTO);

        recipe.setIngredients(ingredients);
        recipeDTO.setIngredients(ingredientsDTO);


        when(recipeMapper.toEntity(recipeDTO)).thenReturn(recipe);
        when(ingredientRepository.findById(1L)).thenReturn(Optional.of(ingredient));
        when(recipeRepository.save(recipe)).thenReturn(recipe);
        when(recipeMapper.toDto(recipe)).thenReturn(recipeDTO);

        RecipeDTO result = recipeService.createDTO(recipeDTO);

        assertNotNull(result);
        assertEquals(recipeDTO, result);
        verify(recipeRepository, times(1)).save(recipe);
        verify(recipeIngredientRepository, times(1)).saveAll(ingredients);
    }

    @Test
    void testUpdate() {
        RecipeDTO updated = new RecipeDTO();
        updated.setId(1L);

        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);

        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setIngredient(ingredient);

        List<RecipeIngredient> ingredients = new ArrayList<>();
        ingredients.add(recipeIngredient);
        recipe.setIngredients(ingredients);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));
        when(recipeMapper.toEntity(updated)).thenReturn(recipe);
        when(ingredientRepository.findById(anyLong())).thenReturn(Optional.of(ingredient));
        when(recipeIngredientRepository.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(Optional.empty());
        when(recipeRepository.save(recipe)).thenReturn(recipe);
        when(recipeMapper.toDto(recipe)).thenReturn(updated);

        RecipeDTO result = recipeService.update(updated);

        assertNotNull(result);
        assertEquals(updated, result);
        verify(recipeRepository, times(1)).save(recipe);
        verify(recipeIngredientRepository, times(1)).saveAll(ingredients);
    }

    @Test
    void testUpdateNotFound() {
        RecipeDTO updated = new RecipeDTO();
        updated.setId(1L);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> recipeService.getById(1L));

        String expectedMessage = "Recipe with id 1 not found.";
        String actualMessage = assertThrows(EntityNotFoundException.class, () -> recipeService.update(updated)).getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testGetById() {
        Long id = 1L;
        Recipe recipe = new Recipe();
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setId(id);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));
        when(recipeMapper.toDto(recipe)).thenReturn(recipeDTO);

        RecipeDTO result = recipeService.getById(id);

        assertNotNull(result);
        assertEquals(recipeDTO, result);
    }

    @Test
    void testGetByIdNotFound() {
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> recipeService.getById(1L));

        String expectedMessage = "Recipe with id 1 not found.";
        String actualMessage = assertThrows(EntityNotFoundException.class, () -> recipeService.getById(1L)).getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testGetAll() {
        Recipe recipe1 = new Recipe();
        Recipe recipe2 = new Recipe();
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(recipe1);
        recipes.add(recipe2);
        RecipeDTO recipeDTO1 = new RecipeDTO();
        RecipeDTO recipeDTO2 = new RecipeDTO();
        List<RecipeDTO> expected = new ArrayList<>();
        expected.add(recipeDTO1);
        expected.add(recipeDTO2);

        when(recipeRepository.findAll()).thenReturn(recipes);
        when(recipeMapper.toDto(recipe1)).thenReturn(recipeDTO1);
        when(recipeMapper.toDto(recipe2)).thenReturn(recipeDTO2);

        List<RecipeDTO> result = recipeService.getAll();

        assertNotNull(result);
        assertEquals(expected.size(), result.size());
        assertEquals(expected.get(0), result.get(0));
        assertEquals(expected.get(1), result.get(1));
    }

    @Test
    void testGetAllByIngredientId() {
        Long ingredientId = 1L;
        Recipe recipe1 = new Recipe();
        Recipe recipe2 = new Recipe();
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(recipe1);
        recipes.add(recipe2);
        RecipeDTO recipeDTO1 = new RecipeDTO();
        RecipeDTO recipeDTO2 = new RecipeDTO();
        List<RecipeDTO> expected = new ArrayList<>();
        expected.add(recipeDTO1);
        expected.add(recipeDTO2);

        when(recipeRepository.findAllByIngredientId(anyLong())).thenReturn(recipes);
        when(recipeMapper.toDto(recipe1)).thenReturn(recipeDTO1);
        when(recipeMapper.toDto(recipe2)).thenReturn(recipeDTO2);

        List<RecipeDTO> result = recipeService.getAllByIngredientId(ingredientId);

        assertNotNull(result);
        assertEquals(expected.size(), result.size());
        assertEquals(expected.get(0), result.get(0));
        assertEquals(expected.get(1), result.get(1));
    }


    @Test
    void testDelete() {
        recipeService.delete(1L);

        verify(recipeRepository, times(1)).deleteById(1L);
    }
}
