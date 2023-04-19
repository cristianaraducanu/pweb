package com.foodiefrenzy.service;

import com.foodiefrenzy.dto.ReviewDTO;
import com.foodiefrenzy.entity.Review;
import com.foodiefrenzy.mapper.ReviewMapper;
import com.foodiefrenzy.repository.ReviewRepository;
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
class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ReviewMapper reviewMapper;

    private ReviewService reviewService;

    @BeforeEach
    void setUp() {
        this.reviewService = new ReviewService(reviewRepository, reviewMapper);
    }

    @Test
    void testCreateDTO() {
        ReviewDTO reviewDTO = ReviewDTO.builder().build();
        Review reviewEntity = new Review();
        Review saved = new Review();
        ReviewDTO expectedDTO = ReviewDTO.builder().build();
        when(reviewMapper.toEntity(reviewDTO)).thenReturn(reviewEntity);
        when(reviewRepository.save(reviewEntity)).thenReturn(saved);
        when(reviewMapper.toDto(saved)).thenReturn(expectedDTO);

        ReviewDTO actualDTO = reviewService.createDTO(reviewDTO);

        verify(reviewMapper).toEntity(reviewDTO);
        verify(reviewRepository).save(reviewEntity);
        verify(reviewMapper).toDto(saved);
        assertEquals(expectedDTO, actualDTO);
    }

    @Test
    void testUpdate() {
        ReviewDTO reviewDTO = ReviewDTO.builder().build();
        reviewDTO.setId(1L);
        Review reviewEntity = new Review();
        reviewEntity.setId(1L);
        Review saved = new Review();
        saved.setId(1L);
        ReviewDTO expectedDTO = ReviewDTO.builder().build();
        expectedDTO.setId(1L);
        when(reviewMapper.toEntity(reviewDTO)).thenReturn(reviewEntity);
        when(reviewRepository.save(reviewEntity)).thenReturn(saved);
        when(reviewMapper.toDto(saved)).thenReturn(expectedDTO);

        ReviewDTO actualDTO = reviewService.update(reviewDTO);

        verify(reviewMapper).toEntity(reviewDTO);
        verify(reviewRepository).save(reviewEntity);
        verify(reviewMapper).toDto(saved);
        assertEquals(expectedDTO, actualDTO);
    }

    @Test
    void testGetById() {
        Review entity = new Review();
        ReviewDTO expectedDTO = ReviewDTO.builder().build();
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(reviewMapper.toDto(entity)).thenReturn(expectedDTO);

        ReviewDTO actualDTO = reviewService.getById(1L);

        verify(reviewRepository).findById(1L);
        verify(reviewMapper).toDto(entity);
        assertEquals(expectedDTO, actualDTO);
    }

    @Test
    void testGetByIdNotFound() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> reviewService.getById(1L));
        verify(reviewRepository).findById(1L);
    }

    @Test
    void testGetAll() {

        List<Review> entities = new ArrayList<>();
        entities.add(new Review());
        List<ReviewDTO> expectedDTOs = new ArrayList<>();
        expectedDTOs.add(ReviewDTO.builder().build());


        when(reviewRepository.findAll()).thenReturn(entities);
        when(reviewMapper.toDto(any(Review.class))).thenReturn(ReviewDTO.builder().build());

        List<ReviewDTO> actualDTOs = reviewService.getAll();

        verify(reviewRepository).findAll();
        verify(reviewMapper, times(entities.size())).toDto(any(Review.class));
        assertEquals(expectedDTOs.size(), actualDTOs.size());
    }

    @Test
    void testDelete() {
        reviewService.delete(1L);

        verify(reviewRepository).deleteById(1L);
    }
}
