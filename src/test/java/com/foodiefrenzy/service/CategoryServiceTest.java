package com.foodiefrenzy.service;

import com.foodiefrenzy.dto.CategoryDTO;
import com.foodiefrenzy.entity.Category;
import com.foodiefrenzy.mapper.CategoryMapper;
import com.foodiefrenzy.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        this.categoryService = new CategoryService(categoryRepository, categoryMapper);
    }

    @Test
    void testCreateDTO() {
        CategoryDTO categoryDTO = new CategoryDTO();
        Category category = new Category();
        Category saved = new Category();
        CategoryDTO expectedDTO = new CategoryDTO();

        when(categoryMapper.toEntity(categoryDTO)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(saved);
        when(categoryMapper.toDto(saved)).thenReturn(expectedDTO);

        CategoryDTO actualDTO = categoryService.createDTO(categoryDTO);

        verify(categoryMapper).toEntity(categoryDTO);
        verify(categoryRepository).save(category);
        verify(categoryMapper).toDto(saved);
        assertEquals(expectedDTO, actualDTO);
    }

    @Test
    void testUpdate() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);

        Category category = new Category();
        category.setId(1L);
        Category saved = new Category();
        saved.setId(1L);
        CategoryDTO expectedDTO = new CategoryDTO();
        expectedDTO.setId(1L);
        when(categoryMapper.toEntity(categoryDTO)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(saved);
        when(categoryMapper.toDto(saved)).thenReturn(expectedDTO);

        CategoryDTO actualDTO = categoryService.update(categoryDTO);

        verify(categoryMapper).toEntity(categoryDTO);
        verify(categoryRepository).save(category);
        verify(categoryMapper).toDto(saved);
        assertEquals(expectedDTO, actualDTO);
    }

    @Test
    void testGetById() {
        Long id = 1L;
        Category entity = new Category();
        CategoryDTO expectedDTO = new CategoryDTO();
        when(categoryRepository.findById(id)).thenReturn(Optional.of(entity));
        when(categoryMapper.toDto(entity)).thenReturn(expectedDTO);

        CategoryDTO actualDTO = categoryService.getById(id);

        verify(categoryRepository).findById(id);
        verify(categoryMapper).toDto(entity);
        assertEquals(expectedDTO, actualDTO);
    }

    @Test
    void testGetByIdNotFound() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> categoryService.getById(1L));
        verify(categoryRepository).findById(1L);
    }

    @Test
    void testGetAll() {
        List<Category> entities = new ArrayList<>();
        entities.add(new Category());

        List<CategoryDTO> expectedDTOs = new ArrayList<>();
        expectedDTOs.add(new CategoryDTO());

        when(categoryRepository.findAll()).thenReturn(entities);
        when(categoryMapper.toDto(any(Category.class))).thenReturn(new CategoryDTO());

        List<CategoryDTO> actualDTOs = categoryService.getAll();

        verify(categoryRepository).findAll();
        verify(categoryMapper, times(entities.size())).toDto(any(Category.class));
        assertEquals(expectedDTOs.size(), actualDTOs.size());
    }

    @Test
    void testDelete() {
        categoryService.delete(1L);

        verify(categoryRepository).deleteById(1L);
    }

}
