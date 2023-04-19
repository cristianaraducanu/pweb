package com.foodiefrenzy.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewDTO {
    private Long id;

    private String comment;

    private int rating;

    private Long recipeId;

    private Long userId;
}
