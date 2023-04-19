package com.foodiefrenzy.controller;

import com.foodiefrenzy.dto.IngredientDTO;
import com.foodiefrenzy.enums.Role;
import com.foodiefrenzy.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ingredient")
@PreAuthorize("hasAnyAuthority(\"" + Role.ROLE_USER + "\")")
public class IngredientController {
    private final IngredientService ingredientService;

    @PreAuthorize("hasAnyAuthority(\"" + Role.ROLE_ADMIN + "\")")
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public IngredientDTO addIngredient(@RequestBody IngredientDTO ingredientDTO) {
        return ingredientService.createDTO(ingredientDTO);
    }

    @GetMapping("/list")
    public ResponseEntity<List<IngredientDTO>> getIngredients() {
        return ResponseEntity.ok(ingredientService.getAll());
    }

    @GetMapping("/list/{recipeId}")
    public ResponseEntity<List<IngredientDTO>> getIngredients(@PathVariable Long recipeId) {
        return ResponseEntity.ok(ingredientService.getAllByRecipeId(recipeId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientDTO> getIngredient(@PathVariable Long id) {
        return ResponseEntity.ok(ingredientService.getById(id));
    }

    @PreAuthorize("hasAnyAuthority(\"" + Role.ROLE_ADMIN + "\")")
    @PutMapping
    public ResponseEntity<IngredientDTO> updateIngredient(@RequestBody IngredientDTO ingredientDTO) {
        return ResponseEntity.ok(ingredientService.update(ingredientDTO));
    }

    @PreAuthorize("hasAnyAuthority(\"" + Role.ROLE_ADMIN + "\")")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ingredientService.delete(id);
    }
}
