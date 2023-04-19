package com.foodiefrenzy.controller;

import com.foodiefrenzy.dto.RecipeDTO;
import com.foodiefrenzy.enums.Role;
import com.foodiefrenzy.service.RecipeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recipe")
@PreAuthorize("hasAnyAuthority(\"" + Role.ROLE_USER + "\")")
public class RecipeController {
    private final RecipeService recipeService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public RecipeDTO addRecipe(@RequestBody RecipeDTO recipeDTO) throws EntityNotFoundException {
        return recipeService.createDTO(recipeDTO);
    }


    @GetMapping("/list")
    @ResponseBody
    public List<RecipeDTO> getRecipes() {
        return recipeService.getAll();
    }

    @GetMapping("/list/{ingredientId}")
    @ResponseBody
    public List<RecipeDTO> getRecipes(@PathVariable Long ingredientId) {
        return recipeService.getAllByIngredientId(ingredientId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> getRecipe(@PathVariable Long id) {
        return ResponseEntity.ok(recipeService.getById(id));
    }

    @PreAuthorize("hasAnyAuthority(\"" + Role.ROLE_ADMIN + "\")")
    @PutMapping
    public ResponseEntity<RecipeDTO> updateRecipe(@RequestBody RecipeDTO recipeDTO) {
        return ResponseEntity.ok(recipeService.update(recipeDTO));
    }

    @PreAuthorize("hasAnyAuthority(\"" + Role.ROLE_ADMIN + "\")")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        recipeService.delete(id);
    }
}
