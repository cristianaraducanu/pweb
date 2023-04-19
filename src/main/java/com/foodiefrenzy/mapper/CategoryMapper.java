package com.foodiefrenzy.mapper;

import com.foodiefrenzy.dto.CategoryDTO;
import com.foodiefrenzy.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends EntityMapper<CategoryDTO, Category> {

}
