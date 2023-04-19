package com.foodiefrenzy.service;

import com.foodiefrenzy.dto.ReviewDTO;
import com.foodiefrenzy.entity.Review;
import com.foodiefrenzy.mapper.ReviewMapper;
import com.foodiefrenzy.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    private final ReviewMapper reviewMapper;

    public ReviewDTO createDTO(ReviewDTO reviewDTO) {
        Review review = reviewMapper.toEntity(reviewDTO);
        return reviewMapper.toDto(reviewRepository.save(review));
    }

    public ReviewDTO update(ReviewDTO updated) {
        Review review = reviewMapper.toEntity(updated);
        return reviewMapper.toDto(reviewRepository.save(review));
    }

    public ReviewDTO getById(Long id) {
        return reviewMapper.toDto(reviewRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Review with id " + id + " not found.")));
    }

    public List<ReviewDTO> getAll() {
        return reviewRepository.findAll().stream()
                .map(reviewMapper::toDto)
                .toList();
    }

    public void delete(Long id) {
        reviewRepository.deleteById(id);
    }
}
