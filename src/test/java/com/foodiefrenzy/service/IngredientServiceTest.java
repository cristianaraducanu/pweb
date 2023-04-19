package com.foodiefrenzy.service;

import com.foodiefrenzy.dto.IngredientDTO;
import com.foodiefrenzy.entity.Ingredient;
import com.foodiefrenzy.mapper.IngredientMapper;
import com.foodiefrenzy.repository.IngredientRepository;
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
class IngredientServiceTest {

    @Mock
    private IngredientRepository ingredientRepository;

    @Mock
    private IngredientMapper ingredientMapper;

    private IngredientService ingredientService;

    @BeforeEach
    void setUp() {
        this.ingredientService = new IngredientService(ingredientRepository, ingredientMapper);
    }

    @Test
    void testCreateDTO() {

        IngredientDTO inputDTO = new IngredientDTO();
        Ingredient inputEntity = new Ingredient();
        Ingredient savedEntity = new Ingredient();
        IngredientDTO expectedDTO = new IngredientDTO();
        when(ingredientMapper.toEntity(inputDTO)).thenReturn(inputEntity);
        when(ingredientRepository.save(inputEntity)).thenReturn(savedEntity);
        when(ingredientMapper.toDto(savedEntity)).thenReturn(expectedDTO);


        IngredientDTO actualDTO = ingredientService.createDTO(inputDTO);


        verify(ingredientMapper).toEntity(inputDTO);
        verify(ingredientRepository).save(inputEntity);
        verify(ingredientMapper).toDto(savedEntity);
        assertEquals(expectedDTO, actualDTO);
    }

    @Test
    void testUpdate() {

        IngredientDTO inputDTO = new IngredientDTO();
        inputDTO.setId(1L);
        Ingredient inputEntity = new Ingredient();
        inputEntity.setId(1L);
        Ingredient savedEntity = new Ingredient();
        savedEntity.setId(1L);
        IngredientDTO expectedDTO = new IngredientDTO();
        expectedDTO.setId(1L);
        when(ingredientMapper.toEntity(inputDTO)).thenReturn(inputEntity);
        when(ingredientRepository.save(inputEntity)).thenReturn(savedEntity);
        when(ingredientMapper.toDto(savedEntity)).thenReturn(expectedDTO);


        IngredientDTO actualDTO = ingredientService.update(inputDTO);


        verify(ingredientMapper).toEntity(inputDTO);
        verify(ingredientRepository).save(inputEntity);
        verify(ingredientMapper).toDto(savedEntity);
        assertEquals(expectedDTO, actualDTO);
    }

    @Test
    void testGetById() {
        Ingredient entity = new Ingredient();
        IngredientDTO expectedDTO = new IngredientDTO();
        when(ingredientRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(ingredientMapper.toDto(entity)).thenReturn(expectedDTO);

        IngredientDTO actualDTO = ingredientService.getById(1L);

        verify(ingredientRepository).findById(1L);
        verify(ingredientMapper).toDto(entity);
        assertEquals(expectedDTO, actualDTO);
    }

    @Test
    void testGetByIdNotFound() {
        when(ingredientRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> ingredientService.getById(1L));
        verify(ingredientRepository).findById(1L);
    }


    @Test
    void testGetAll() {
        List<Ingredient> entities = new ArrayList<>();
        entities.add(new Ingredient());
        List<IngredientDTO> expectedDTOs = new ArrayList<>();
        expectedDTOs.add(new IngredientDTO());
        when(ingredientRepository.findAll()).thenReturn(entities);
        when(ingredientMapper.toDto(any(Ingredient.class))).thenReturn(new IngredientDTO());

        List<IngredientDTO> actualDTOs = ingredientService.getAll();

        verify(ingredientRepository).findAll();
        verify(ingredientMapper, times(entities.size())).toDto(any(Ingredient.class));
        assertEquals(expectedDTOs.size(), actualDTOs.size());
    }

    @Test
    void testGetAllByRecipeId() {
        Long recipeId = 1L;
        List<Ingredient> entities = new ArrayList<>();
        entities.add(new Ingredient());
        List<IngredientDTO> expectedDTOs = new ArrayList<>();
        expectedDTOs.add(new IngredientDTO());
        when(ingredientRepository.findAllByRecipeId(recipeId)).thenReturn(entities);
        when(ingredientMapper.toDto(any(Ingredient.class))).thenReturn(new IngredientDTO());

        List<IngredientDTO> actualDTOs = ingredientService.getAllByRecipeId(recipeId);

        verify(ingredientRepository).findAllByRecipeId(recipeId);
        verify(ingredientMapper, times(entities.size())).toDto(any(Ingredient.class));
        assertEquals(expectedDTOs.size(), actualDTOs.size());
    }

    @Test
    void testDelete() {
        ingredientService.delete(1L);

        verify(ingredientRepository).deleteById(1L);
    }
}
