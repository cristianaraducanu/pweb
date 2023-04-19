package com.foodiefrenzy.service;

import com.foodiefrenzy.dto.IngredientDTO;
import com.foodiefrenzy.entity.Ingredient;
import com.foodiefrenzy.mapper.IngredientMapper;
import com.foodiefrenzy.repository.IngredientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    private final IngredientMapper ingredientMapper;

    public IngredientDTO createDTO(IngredientDTO ingredientDTO) {
        Ingredient ingredient = ingredientMapper.toEntity(ingredientDTO);
        return ingredientMapper.toDto(ingredientRepository.save(ingredient));
    }

    public IngredientDTO update(IngredientDTO updated) {
        Ingredient ingredient = ingredientMapper.toEntity(updated);
        return ingredientMapper.toDto(ingredientRepository.save(ingredient));
    }

    public IngredientDTO getById(Long id) {
        return ingredientMapper.toDto(ingredientRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Ingredient with id " + id + " not found.")));
    }

    public List<IngredientDTO> getAll() {
        return ingredientRepository.findAll().stream()
                .map(ingredientMapper::toDto)
                .toList();
    }

    public List<IngredientDTO> getAllByRecipeId(Long recipeId) {
        return ingredientRepository.findAllByRecipeId(recipeId)
                .stream()
                .map(ingredientMapper::toDto)
                .toList();
    }

    public void delete(Long id) {
        ingredientRepository.deleteById(id);
    }

}
