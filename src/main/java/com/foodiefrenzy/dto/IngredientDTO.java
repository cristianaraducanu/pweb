package com.foodiefrenzy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class IngredientDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;
}
