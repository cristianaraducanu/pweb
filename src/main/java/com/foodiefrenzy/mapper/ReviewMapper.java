package com.foodiefrenzy.mapper;

import com.foodiefrenzy.dto.ReviewDTO;
import com.foodiefrenzy.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ReviewMapper.class)
public interface ReviewMapper extends EntityMapper<ReviewDTO, Review> {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "recipe.id", target = "recipeId")
    ReviewDTO toDto(Review entity);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "recipeId", target = "recipe.id")
    Review toEntity(ReviewDTO dto);

}
