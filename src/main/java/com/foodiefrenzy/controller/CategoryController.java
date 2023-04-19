package com.foodiefrenzy.controller;

import com.foodiefrenzy.dto.CategoryDTO;
import com.foodiefrenzy.enums.Role;
import com.foodiefrenzy.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
@PreAuthorize("hasAnyAuthority(\"" + Role.ROLE_USER + "\")")
public class CategoryController {
    private final CategoryService categoryService;


    @PreAuthorize("hasAnyAuthority(\"" + Role.ROLE_ADMIN + "\")")
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CategoryDTO addCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.createDTO(categoryDTO);
    }

    @GetMapping("/list")
    public ResponseEntity<List<CategoryDTO>> getCategorys() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @PreAuthorize("hasAnyAuthority(\"" + Role.ROLE_ADMIN + "\")")
    @PutMapping
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.update(categoryDTO));
    }

    @PreAuthorize("hasAnyAuthority(\"" + Role.ROLE_ADMIN + "\")")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
