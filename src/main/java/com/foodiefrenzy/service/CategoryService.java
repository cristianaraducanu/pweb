package com.foodiefrenzy.service;

import com.foodiefrenzy.dto.CategoryDTO;
import com.foodiefrenzy.entity.Category;
import com.foodiefrenzy.mapper.CategoryMapper;
import com.foodiefrenzy.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    public CategoryDTO createDTO(CategoryDTO categoryDTO) {
        Category category = categoryMapper.toEntity(categoryDTO);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    public CategoryDTO update(CategoryDTO updated) {
        Category category = categoryMapper.toEntity(updated);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    public CategoryDTO getById(Long id) {
        return categoryMapper.toDto(categoryRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Category with id " + id + " not found.")));
    }

    public List<CategoryDTO> getAll() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
