package com.foodiefrenzy.controller;

import com.foodiefrenzy.dto.ReviewDTO;
import com.foodiefrenzy.enums.Role;
import com.foodiefrenzy.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
@PreAuthorize("hasAnyAuthority(\"" + Role.ROLE_USER + "\")")
public class ReviewController {
    private final ReviewService reviewService;

    @PreAuthorize("hasAnyAuthority(\"" + Role.ROLE_ADMIN + "\")")
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReviewDTO addReview(@RequestBody ReviewDTO reviewDTO) {
        return reviewService.createDTO(reviewDTO);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ReviewDTO>> getReviews() {
        return ResponseEntity.ok(reviewService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReview(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getById(id));
    }

    @PutMapping
    public ResponseEntity<ReviewDTO> updateReview(@RequestBody ReviewDTO reviewDTO) {
        return ResponseEntity.ok(reviewService.update(reviewDTO));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        reviewService.delete(id);
    }
}
